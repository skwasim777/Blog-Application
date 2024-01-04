package com.bolgapplication.services;

import java.util.List;

import com.bolgapplication.entities.Category;
import com.bolgapplication.payloads.CategoryDto;

public interface CategoryService {
		CategoryDto createCategory(CategoryDto categoryDto);
		CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryd);
		void deleteCategory(Integer categoryId);
		CategoryDto getCategory(Integer categId);
		List<CategoryDto> getAllCategory();
}
