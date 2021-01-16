package com.luka.trackerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.trackerapp.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
