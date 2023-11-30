package com.example.BookStoreFinal.response;

import com.example.BookStoreFinal.dto.Detail;
import com.example.BookStoreFinal.model.Category;
import com.example.BookStoreFinal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class CategoryResponse {
    
    @Autowired
    CategoryService categoryService;

    public ResponseEntity<?> createCategory(Category category){
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newDepositURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{categoryId}").buildAndExpand(category.getId()).toUri();
        responseHeaders.setLocation(newDepositURI);

        Detail detail = new Detail();
        detail.setData(categoryService.createCategory(category));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Category successfully created for " + category.getName());

        return new ResponseEntity<>(detail, responseHeaders, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getCategoryById(Long categoryId){
        Detail detail = new Detail();
        detail.setData(categoryService.getCategoryById(categoryId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Category successfully retrieved for id: " + categoryId);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllCategories(){
        Detail detail = new Detail();
        detail.setData(categoryService.getAllCategories());
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Successfully retrieved all categories");

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

}
