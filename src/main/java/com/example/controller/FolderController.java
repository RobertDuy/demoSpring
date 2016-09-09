package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.RecipientFolder;
import com.example.services.RecipientFolderService;

@Controller
@RequestMapping("/folder")
public class FolderController {
	
	@Autowired
	RecipientFolderService recipientFolderService;
	
	@RequestMapping(value = "/get_recipient_folder", method = RequestMethod.GET)
	public @ResponseBody List<RecipientFolder> getListFolder(HttpServletRequest request, ModelMap model) {
		Object token = request.getSession().getAttribute(RecipientController.ACTOKEN);
		String sessionToken = (token != null) ? (String) token : null;
		return recipientFolderService.getListRecipientFolder(sessionToken, true);
	}
}
