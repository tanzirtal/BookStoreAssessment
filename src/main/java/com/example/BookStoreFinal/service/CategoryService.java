package com.example.BookStoreFinal.service;

import com.example.BookStoreFinal.exception.BookNotFoundByIdException;
import com.example.BookStoreFinal.model.Book;
import com.example.BookStoreFinal.model.Category;
import com.example.BookStoreFinal.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public Category createCategory(Category category){


        category.setName(category.getName());
        category.setId(category.getId());

        logger.info("Category successfully created");
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            logger.error("Category with Id: " + categoryId + " not found.");
            throw new BookNotFoundByIdException("Category with Id: " + categoryId + " not found.");
        });

        logger.info("Category successfully retrieved");
        return category;
    }

    public Category updateCategory(Long categoryId, Category updatedCategory){
        Category existingCategory = getCategoryById(categoryId);

        existingCategory.setName(updatedCategory.getName());

        logger.info("Category information successfully updated");
        categoryRepository.save(existingCategory);
        return existingCategory;

    }

    public Iterable<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            logger.error("List of categories is empty");
            throw new BookNotFoundByIdException("List of categories is empty");
        }
        logger.info("All categories successfully retrieved");

        return categoryRepository.findAll();
    }
}
