package com.luka.trackerapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luka.trackerapp.AdminUser;
import com.luka.trackerapp.model.Role;
import com.luka.trackerapp.model.User;
import com.luka.trackerapp.service.UserService;

@Controller
public class AppController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String indexPage() {
		
		return "index";
	}
	
	@GetMapping("/home")
	public String homePage(HttpServletRequest request, Model model) {
		User user = userService.findByName(request.getUserPrincipal().getName());
		
		model.addAttribute("admin", AdminUser.isAdmin(user));
		model.addAttribute("userFirstName", user.getFirstName());
		
		return "home";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		
		return "login";
	}
	
	@GetMapping("/403")
	public String accessDeniedPage() {
		
		return "403";
	}
	
	@RequestMapping(path = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logoutPage() {
		
		return "logout";
	}
	
}
