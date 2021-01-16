package com.luka.trackerapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.luka.trackerapp.model.Category;
import com.luka.trackerapp.model.Expense;
import com.luka.trackerapp.service.CategoryService;
import com.luka.trackerapp.service.ExpenseService;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/expenses")
	public String expensePage(Model model) {
		List<Expense> expenseList = expenseService.findAll();
		model.addAttribute("expenseList", expenseList);
		
		double totalSum = 0;
		for(Expense e : expenseList) {
			totalSum += e.getPrice();
		}
		totalSum = Math.round(totalSum * 100.0) / 100.0;
		model.addAttribute("totalSum", totalSum);
		
	
		return "expense";
	}
	
	@GetMapping("/addExpense")
	public String showExpenseForm(Model model) {
		Expense expense = new Expense();
		
		LocalDate localDate = LocalDate.now();
		expense.setDate(DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate));
		
		model.addAttribute("expense", expense);
		
		List<Category> listCategory = categoryService.findAll();
		model.addAttribute("listCategory", listCategory);
		
		return "expense_form";
	}
	
	@PostMapping("/saveNewExpense")
	public String saveNewExpense(Expense expense) {
		expenseService.save(expense);
		
		return "redirect:/expenses";
	}
	
	@GetMapping("/editExpense/{id}")
	public String showEditExpenseForm(@PathVariable Integer id, Model model) {
		Expense expense = expenseService.findById(id);
		model.addAttribute("expense", expense);
		
		List<Category> listCategory = categoryService.findAll();
		model.addAttribute("listCategory", listCategory);
		
		return "expense_form";
	}
	
	@GetMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable Integer id) {
		expenseService.deleteById(id);
		
		return "redirect:/expenses";
	}
	
}
