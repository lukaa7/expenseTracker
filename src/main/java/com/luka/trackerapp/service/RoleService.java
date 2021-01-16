package com.luka.trackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luka.trackerapp.model.Role;
import com.luka.trackerapp.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	public List<Role> findAll() {
		return repository.findAll();
	}

	public Role findRoleById(int id) {
		return repository.findRoleById(id);
	}

	public Role findRoleByRole(String string) {
		return repository.findRoleByRole(string);
	}
	
	
}
