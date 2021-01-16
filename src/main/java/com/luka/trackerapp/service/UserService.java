package com.luka.trackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luka.trackerapp.model.User;
import com.luka.trackerapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public void save(User user) {
		repository.save(user);
	}

	public User findById(Integer id) {
		return repository.findById(id).get();
	}
	
}
