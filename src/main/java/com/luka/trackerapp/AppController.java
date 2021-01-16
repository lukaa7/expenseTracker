package com.luka.trackerapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	
	@GetMapping("/home")
	public String homePage() {
		
		return "home";
	}
	
	@GetMapping("/login2")
	public String loginPage() {
		
		return "login";
	}
	
}
