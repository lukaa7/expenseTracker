package com.luka.trackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luka.trackerapp.model.Expense;
import com.luka.trackerapp.model.User;
import com.luka.trackerapp.repository.ExpenseRepository;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository repository;


	public Page<Expense> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 5);
		return repository.findAll(pageable);
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

	public List<Expense> findAllExpenses(Integer userId, String keyword) {
			return repository.findByDetailsAndUser(userId, keyword != null ? keyword : "");

	}

	public double getTotalSum(List<Expense> expenseList) {
		double totalSum = 0;
		for(Expense e : expenseList) {
			totalSum += e.getPrice();
		}
		return Math.round(totalSum * 100.0) / 100.0;
	}
	
}
