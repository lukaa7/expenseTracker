package com.luka.trackerapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.luka.trackerapp.AdminUser;
import com.luka.trackerapp.BcryptEncoder;
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
	public String showAllUsers(Model model, HttpServletRequest request) {
		
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		User user = userService.findByName(request.getUserPrincipal().getName());
		model.addAttribute("admin", AdminUser.isAdmin(user));
		
		model.addAttribute("userFirstName", user.getFirstName());
		
		return "user";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable Integer id, Model model, HttpServletRequest request) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		
		User principalUser = userService.findByName(request.getUserPrincipal().getName());
		model.addAttribute("userFirstName", principalUser.getFirstName());
		
		model.addAttribute("admin", AdminUser.isAdmin(principalUser));
		
		return "user_edit_form";
	}
	
	@PostMapping("/saveEditUser")
	public String saveEditUser(User user) {
		user.setPassword(BcryptEncoder.encode(user.getPassword()));
		userService.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		userService.deleteById(id);
		
		return "redirect:/users";
	}
	
	//Ovo metode ispod su za principal user-a
	
	
	@GetMapping("/signup")
	public String newUserRegistration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "principal_form";
	}
	
	@PostMapping("/saveNewUser")
	public String saveNewUser(User user) {
		Role role = roleService.findRoleByRole("USER");
		user.addRole(role);
		user.setEnabled(true);
		user.setPassword(BcryptEncoder.encode(user.getPassword()));
		userService.save(user);
		
		return "signup_success";
	}
	
	@GetMapping("/editmyprofile")
	public String editMyProfilePage(Model model, HttpServletRequest request) {
		User user = userService.findByName(request.getUserPrincipal().getName());
		model.addAttribute("user", user);
		
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		
		model.addAttribute("admin", AdminUser.isAdmin(user));
		
		return "principal_edit";
	}
	
	@PostMapping("/savemyedit")
	public String saveMyEdit(User user, Model model) {
		user.setPassword(BcryptEncoder.encode(user.getPassword()));
		userService.save(user);
		
		return "redirect:/myprofile";
	}
	
	@GetMapping("/myprofile")
	public String showMyProfile(Model model, HttpServletRequest request) {
		User user = userService.findByName(request.getUserPrincipal().getName());
		model.addAttribute("user", user);
		
		model.addAttribute("admin", AdminUser.isAdmin(user));
		
		return "principal_profile";
	}
	
}
