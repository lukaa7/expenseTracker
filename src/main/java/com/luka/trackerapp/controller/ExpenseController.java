package com.luka.trackerapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.luka.trackerapp.AdminUser;
import com.luka.trackerapp.model.Category;
import com.luka.trackerapp.model.Expense;
import com.luka.trackerapp.model.User;
import com.luka.trackerapp.service.CategoryService;
import com.luka.trackerapp.service.ExpenseService;
import com.luka.trackerapp.service.UserService;

// expenseorigin

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/expenses")
	public String expensePage(Model model,
				HttpServletRequest request,
				@Param("keyword") String keyword) {
		User user = userService.findByName(request.getUserPrincipal().getName());
		
		List<Expense> expenseList = expenseService.findAllExpenses(user.getId(), keyword);
		model.addAttribute("keqword", keyword);
		
		
		
		model.addAttribute("expenseList", expenseList);
		
		model.addAttribute("totalSum", expenseService.getTotalSum(expenseList));
		
		model.addAttribute("admin", AdminUser.isAdmin(user));
		model.addAttribute("userFirstName", user.getFirstName());
		return "expense";
	}
	
	@GetMapping("/addExpense")
	public String showExpenseForm(Model model, HttpServletRequest request) {
		Expense expense = new Expense();
		
		LocalDate localDate = LocalDate.now();
		expense.setDate(DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate));
		
		model.addAttribute("expense", expense);
		
		List<Category> listCategory = categoryService.findAll();
		model.addAttribute("listCategory", listCategory);
		
		User user = userService.findByName(request.getUserPrincipal().getName());
		model.addAttribute("userFirstName", user.getFirstName());
		model.addAttribute("admin", AdminUser.isAdmin(user));
		
		return "expense_form";
	}
	
	@PostMapping("/saveNewExpense")
	public String saveNewExpense(Expense expense, HttpServletRequest request) {
		expense.setUserid(userService.findByName(request.getUserPrincipal().getName()).getId());
		expenseService.save(expense);
		
		return "redirect:/expenses";
	}
	
	@GetMapping("/editExpense/{id}")
	public String showEditExpenseForm(@PathVariable Integer id, Model model, HttpServletRequest request) {
		Expense expense = expenseService.findById(id);
		model.addAttribute("expense", expense);
		
		List<Category> listCategory = categoryService.findAll();
		model.addAttribute("listCategory", listCategory);
		
		User user = userService.findByName(request.getUserPrincipal().getName());
		model.addAttribute("userFirstName", user.getFirstName());
		model.addAttribute("admin", AdminUser.isAdmin(user));
		
		return "expense_form";
	}
	
	@GetMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable Integer id) {
		expenseService.deleteById(id);
		
		return "redirect:/expenses";
	}
	
}
