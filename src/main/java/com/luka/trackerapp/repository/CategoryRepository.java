package com.luka.trackerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luka.trackerapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
