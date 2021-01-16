package com.luka.trackerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.luka.trackerapp.model.Role;
import com.luka.trackerapp.model.User;
import com.luka.trackerapp.service.RoleService;
import com.luka.trackerapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/users")
	public String showAllUsers(Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		return "user";
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userService.deleteById(id);
		
		return "redirect:/users";
	}
	
	@GetMapping("/signUp")
	public String newUserRegistration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "user_form";
	}
	
	@PostMapping("/saveNewUser")
	public String saveNewUser(User user) {
		Role role = roleService.findRoleByRole("USER");
		user.addRole(role);
		userService.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable Integer id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		
		return "user_edit_form";
	}
	
	@PostMapping("/saveEditUser")
	public String saveEditUser(User user) {
		userService.save(user);
		
		return "redirect:/users";
	}
	
}
