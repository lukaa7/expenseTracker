package com.luka.trackerapp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/login2")
	public String loginPage() {
		
		return "login";
	}
	
	@GetMapping("/403")
	public String accessDeniedPage() {
		
		return "403";
	}
	
}
