package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.criteria.Criteria;
import com.example.criteria.Operand;
import com.example.model.Recipient;
import com.example.model.RecipientFolder;
import com.example.services.RecipientFolderService;
import com.example.services.RecipientQueryService;
import com.example.services.RecipientWriterService;

@Controller
@RequestMapping("/recipient")
public class RecipientController {
	public static final String ACTOKEN = "actoken";
	public static final String CACHE_NAME = "recipientCache";
	public static final String[] IMPORT_KEYS = new String[]{"email", "firstName", "lastName", "tonariwaid"};

	@Autowired
	RecipientQueryService recipientQueryService;
	@Autowired
	RecipientWriterService recipientWriterService;
	@Autowired
	RecipientFolderService recipientFolderService;
	
	CacheManager cacheManager = CacheManager.newInstance();
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String recipientQuery(HttpServletRequest request, ModelMap model) {
		Object token = request.getSession().getAttribute(ACTOKEN);
		String sessionToken = (token != null) ? (String) token : null;
		model.put("acToken", sessionToken);
		if(sessionToken != null){
			List<RecipientFolder> listFolder = recipientFolderService.getListRecipientFolder(sessionToken, true);
			model.put("listFolder", listFolder);
		}
		return "recipient_query";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST, consumes="application/json; charset=UTF-8", headers = "content-type=application/x-www-form-urlencoded", produces="application/json")
	public @ResponseBody Object recipientQuerySubmit(@RequestBody String data, HttpServletRequest request) {
		try{
			JSONObject jsonObject = new JSONObject(data);
			JSONObject dataJson = jsonObject.getJSONObject("data");
			String mode = dataJson.getString("mode");
			String folder = dataJson.getString("folder");
			String[] listOperand = jsonStringToArray((String)dataJson.get("listOperand"));
			String[] listKey = jsonStringToArray((String)dataJson.get("listKey"));
			String[] listValue = jsonStringToArray((String)dataJson.get("listValue"));
			
			Criteria recipientCriterias = new Criteria();
			for(int i = 0; i < listKey.length; i++){
				recipientCriterias.setOperator(listKey[i], Operand.fromValue(listOperand[i]), listValue[i]);
			}
			recipientCriterias.setOperator("[@folder-id]", Operand.EQUAL, folder);
			Object token = request.getSession().getAttribute(ACTOKEN);
			if(token == null) return null;
			String sessionToken = (String) token;
			
			Cache cache = cacheManager.getCache(CACHE_NAME);
			if(mode != null){
				if(mode.equals(RecipientQueryService.GET_ONE)){
					Recipient result = recipientQueryService.getEntity(sessionToken, recipientCriterias);
					cache.put(new Element(mode + sessionToken, result));
					return result; 
				}else if(mode.equals(RecipientQueryService.GET_MUTIPLE)){
					List<Recipient> results = recipientQueryService.getListEntity(sessionToken, recipientCriterias);
					cache.put(new Element(mode + sessionToken, results));
					return results;
				}else if(mode.equals(RecipientQueryService.GET_COUNT)){
					return recipientQueryService.getCount(sessionToken, recipientCriterias);
				}
			}
		}catch(Exception e){
			System.out.print("Error parser : " + e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "/writer", method = RequestMethod.GET)
	public String recipientWriter(HttpServletRequest request, ModelMap model) {
		Object token = request.getSession().getAttribute(ACTOKEN);
		String sessionToken = (token != null) ? (String) token : null;
		model.put("acToken", sessionToken);
		if(sessionToken != null){
			List<RecipientFolder> listFolder = recipientFolderService.getListRecipientFolder(sessionToken, true);
			model.put("listFolder", listFolder);
		}
		return "recipient_writer";
	}
	
	@RequestMapping(value = "/writer/submit", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file,
			@RequestParam("folderId") String folderId, HttpServletRequest request) {
		Object token = request.getSession().getAttribute(ACTOKEN);
		if(token == null) return "Please login first!";
		if (!file.isEmpty()) {
			try {
				List<Recipient> recipients = new ArrayList<Recipient>();
				InputStream inputStream = file.getInputStream();
				int data = inputStream.read();
				StringBuffer line = new StringBuffer("");
				while(data != -1){
					if((char)data != '\n'){
						line.append((char)data);
					}else{
						String[] values = line.toString().split(",");
						Recipient reci = new Recipient();
						for(int i=0; i < values.length; i++){
							Field field = Recipient.class.getDeclaredField(IMPORT_KEYS[i]);
					        field.setAccessible(true);
					        field.set(reci, values[i]);
						}
						recipients.add(reci);
						line.setLength(0);
					}
					System.out.print((char)data);
					data = inputStream.read();
				}
				boolean result = recipientWriterService.writeRecipient((String)token, recipients, folderId);
				if(result){
					return "Insert sucessfull!";
				}else{
					return "Error occurr during import! Please check import file data and reimport again!";
				}
			} catch (Exception e) {
				return "Error occurr during import! Please check import file data and reimport again!";
			}
		} else {
			return "Choose .csv file to upload, plz :)";
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public @ResponseBody void exportCVS(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		Object token = request.getSession().getAttribute(RecipientController.ACTOKEN);
		if(token == null) return;
		String sessionToken = (String)token;
		Cache cache = cacheManager.getCache(CACHE_NAME);
		String mode = (String) request.getParameter("mode");
		List<Recipient> cacheLstRecipient = null;
		if(RecipientQueryService.GET_ONE.equals(mode)){
			cacheLstRecipient = new ArrayList<Recipient>();
			cacheLstRecipient.add((Recipient)cache.get(mode + sessionToken).getObjectValue());
		}else if(RecipientQueryService.GET_MUTIPLE.equals(mode)){
			cacheLstRecipient = (List<Recipient>) cache.get(mode + sessionToken).getObjectValue();
		}
		
		String csvFileName = "export.csv";
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
        response.setHeader(headerKey, headerValue);
 
        ServletOutputStream writer = response.getOutputStream();
        writer.print("email,firstName,lastName,tonariwaid");
        writer.println();
        StringBuffer line = new StringBuffer("");
        for(int i = 0; i < cacheLstRecipient.size(); i++){
        	Recipient recipient = cacheLstRecipient.get(i);
        	line.append(recipient.getEmail());
        	line.append(",");
        	line.append(recipient.getFirstName());
        	line.append(",");
        	line.append(recipient.getLastName());
        	line.append(",");
        	line.append(recipient.getTonariwaid());
        	writer.print(line.toString());
        	writer.println();
        	line.setLength(0);
        }
        writer.flush();
        writer.close();
	}
	
	private String[] jsonStringToArray(String jsonString) throws JSONException {
	    ArrayList<String> stringArray = new ArrayList<String>();

	    JSONArray jsonArray = new JSONArray(jsonString);

	    for (int i = 0; i < jsonArray.length(); i++) {
	        stringArray.add(jsonArray.getString(i));
	    }
	    return stringArray.toArray(new String[0]);
	}
	
}
