package com.luka.trackerapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.luka.trackerapp.AdminUser;
import com.luka.trackerapp.model.Category;
import com.luka.trackerapp.model.User;
import com.luka.trackerapp.repository.CategoryRepository;
import com.luka.trackerapp.service.UserService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/newCategory")
	public String showCategoryForm(Model model, HttpServletRequest request) {
		Category category = new Category();
		model.addAttribute("category", category);
		
		User user = userService.findByName(request.getUserPrincipal().getName());
		model.addAttribute("userFirstName", user.getFirstName());
		
		model.addAttribute("admin", AdminUser.isAdmin(user));
		
		return "category_form";
	}
	
	@PostMapping("/saveNewCategory")
	public String saveNewCategory(Category category) {
		categoryRepository.save(category);
		
		return "redirect:/addExpense";
	}
	
}
