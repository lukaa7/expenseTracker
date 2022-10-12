package com.luka.trackerapp.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luka.trackerapp.model.Expense;
import com.luka.trackerapp.model.User;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {


	@Query(value = "SELECT e FROM Expense e WHERE e.userid = :usrid "
			+ "AND lower(e.details) LIKE  %:keyword%")
	List<Expense> findByDetailsAndUser(@Param("usrid") Integer userId, @Param("keyword") String keyword);
	
}
