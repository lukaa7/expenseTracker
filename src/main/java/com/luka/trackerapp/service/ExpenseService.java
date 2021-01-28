package com.luka.trackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luka.trackerapp.model.Expense;
import com.luka.trackerapp.model.User;
import com.luka.trackerapp.repository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository repository;

	public List<Expense> findAll() {
		return repository.findAll();
	}

	public void save(Expense expense) {
		repository.save(expense);
	}

	public Expense findById(Integer id) {
		return repository.findById(id).get();
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public List<Expense> findByUser(User user) {
		return repository.findByUser(user);
	}

	public double getTotalSum(List<Expense> expenseList) {
		double totalSum = 0;
		for(Expense e : expenseList) {
			totalSum += e.getPrice();
		}
		return Math.round(totalSum * 100.0) / 100.0;
	}
	
}
