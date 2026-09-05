package es.hua.exercise.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController 
{
	
	@GetMapping("/login")
	public String showLogin() 
	{
		return "login";
	}
	
	@RequestMapping(value = "/logged", method = RequestMethod.GET)
	public String redirect(HttpServletRequest httpServletRequest, Model model)
	{
		if(httpServletRequest.isUserInRole("ADMIN"))
		{
			return "admin";
		}
		else
		{
			return "user";
		}
	}
}


