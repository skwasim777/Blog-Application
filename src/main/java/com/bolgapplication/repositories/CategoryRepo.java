package com.bolgapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolgapplication.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
