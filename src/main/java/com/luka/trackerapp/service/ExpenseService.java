package com.luka.trackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luka.trackerapp.model.Expense;
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
	
}
