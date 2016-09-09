package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Recipient;
import com.example.model.ResultObject;
import com.example.services.LogonService;
import com.example.services.RecipientWriterService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/register")
public class RegisterController {
	public static final String ACTOKEN = "actoken";
	private Gson gson = new Gson();
	
	@Autowired
	RecipientWriterService recipientWriterService;
	@Autowired
	LogonService logonService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes="application/json; charset=UTF-8", produces="application/json")
	public @ResponseBody ResultObject registRecipient(@RequestBody String data, HttpServletRequest request){
		String token = logonService.getSessionToken("admin", "$1$abcd#adm");
		String sessionToken = (token != null) ? (String) token : null;
		if(sessionToken == null){
			return new ResultObject("-1", "Please login first!");
		}
		JSONObject postDataJson = new JSONObject(data);
		ResultObject result = null;
		try{
			JSONArray recipientsJson = postDataJson.getJSONArray("recipients");
			List<Recipient> recipients = new ArrayList<Recipient>();
			for(int i = 0; i < recipientsJson.length(); i++){
				Recipient recipient = gson.fromJson(recipientsJson.getJSONObject(i).toString(), Recipient.class);
				recipients.add(recipient);
			}
			recipientWriterService.writeRecipient((String)token,recipients, null);
			result = new ResultObject("OK", "Regist sucessfully!");
		}catch(Exception e){
			result = new ResultObject("FAILED", e.getMessage());
		}
		return result;
	}
	
}
