package com.luka.trackerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.trackerapp.model.Expense;
import com.luka.trackerapp.model.User;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	List<Expense> findByUser(User user);
	
}
