package com.luka.trackerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.trackerapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
}
