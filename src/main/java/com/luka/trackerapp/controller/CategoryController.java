package com.luka.trackerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.luka.trackerapp.model.Category;
import com.luka.trackerapp.repository.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/newCategory")
	public String showCategoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		
		return "category_form";
	}
	
	@PostMapping("/saveNewCategory")
	public String saveNewCategory(Category category) {
		categoryRepository.save(category);
		
		return "redirect:/addExpense";
	}
	
}
