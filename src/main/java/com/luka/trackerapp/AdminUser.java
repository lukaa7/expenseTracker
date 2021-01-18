package com.luka.trackerapp;

import com.luka.trackerapp.model.Role;
import com.luka.trackerapp.model.User;

public class AdminUser {

	public static boolean isAdmin(User user) {
		
		for(Role role : user.getRoles()) {
			if(role.getRole().equalsIgnoreCase("ADMIN")) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
