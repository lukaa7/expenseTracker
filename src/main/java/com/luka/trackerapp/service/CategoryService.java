package com.luka.trackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luka.trackerapp.model.Category;
import com.luka.trackerapp.model.Expense;
import com.luka.trackerapp.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Integer id) {
		return repository.findById(id).get();
	}
	
}