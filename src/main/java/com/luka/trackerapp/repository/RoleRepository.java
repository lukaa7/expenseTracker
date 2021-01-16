package com.luka.trackerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.trackerapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleById(Integer id);
	Role findRoleByRole(String role);
	
}
