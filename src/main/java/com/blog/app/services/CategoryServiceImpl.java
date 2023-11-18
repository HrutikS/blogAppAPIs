package com.blog.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = toCategory(categoryDto);
		Category savedCategory =  categoryRepository.save(category);
		return toCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = categoryRepository.save(category);
		return toCategoryDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		categoryRepository.delete(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> listOfCategories = categoryRepository.findAll();
		List<CategoryDto> listOfCategoryDtos = listOfCategories.stream().map(category -> toCategoryDto(category)).collect(Collectors.toList());
		return listOfCategoryDtos;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		return toCategoryDto(category);
	}
	
	
	//Mapper methods
	public Category toCategory(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto, Category.class);
	}
	
	public CategoryDto toCategoryDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

}
