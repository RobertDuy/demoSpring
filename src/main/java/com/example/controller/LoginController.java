package com.example.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.services.LogonService;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	LogonService logonService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/sumbit", method = RequestMethod.GET)
	public String submit(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/sumbit/ajax", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody String loginAjax(HttpServletRequest request,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {
		String token = logonService.getSessionToken(username, password);
		request.getSession().setAttribute(RecipientController.ACTOKEN, token);
		return token;
	}

}
