package com.bolgapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolgapplication.entities.Category;
import com.bolgapplication.exceptions.ResourceNotFoundException;
import com.bolgapplication.payloads.CategoryDto;
import com.bolgapplication.repositories.CategoryRepo;
import com.bolgapplication.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo caRepo;
	@Autowired
	private ModelMapper moMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.moMapper.map(categoryDto, Category.class);
		Category addedCat = this.caRepo.save(cat);
		return this.moMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.caRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDesc(categoryDto.getCategoryDesc());
		Category updated = this.caRepo.save(cat);
		return this.moMapper.map(updated, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.caRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.caRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categId) {
		Category category = this.caRepo.findById(categId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categId));
		return this.moMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.caRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.moMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return catDtos;
	}

}
