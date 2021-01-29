package com.luka.trackerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luka.trackerapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findByEmail(String email);

	@Query(value = "SELECT u FROM User u WHERE u.username = :keyword "
			+ "OR u.email = :keyword "
			+ "OR u.firstName = :keyword "
			+ "OR lastName = :keyword")
			
	List<User> searchUser(@Param("keyword") String keyword);
	
}
