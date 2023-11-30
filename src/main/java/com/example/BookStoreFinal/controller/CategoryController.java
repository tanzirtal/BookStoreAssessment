package com.example.BookStoreFinal.controller;

import com.example.BookStoreFinal.model.Category;
import com.example.BookStoreFinal.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    CategoryResponse categoryResponse;

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        return categoryResponse.createCategory(category);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId){
        return categoryResponse.getCategoryById(categoryId);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){
        return categoryResponse.getAllCategories();
    }
}
