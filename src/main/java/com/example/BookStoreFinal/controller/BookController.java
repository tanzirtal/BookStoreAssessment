package com.example.BookStoreFinal.controller;

import com.example.BookStoreFinal.model.Book;
import com.example.BookStoreFinal.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookResponse bookResponse;

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book){
        return bookResponse.createBook(book);
    }

    @GetMapping("/books/by-id/{bookId}")
    public ResponseEntity<?> getBookBookById(@PathVariable Long bookId){
        return bookResponse.getBookById(bookId);
    }

//    @GetMapping("/books/by-sku/{sku}")
//    public ResponseEntity<?> getBookBySku(@PathVariable Long sku){
//        return bookResponse.getBookBySku(sku);
//    }
//
//    @GetMapping("/books/by-name/{name}")
//    public ResponseEntity<?> getBookByName(@PathVariable String name){
//        return bookResponse.getBookByName(name);
//    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(@RequestParam(required = false) String name, @RequestParam(required = false) Long sku){
        return bookResponse.getAllBooks(name, sku);
    }

    @GetMapping("/books/by-category/{categoryId}")
    public ResponseEntity<?> getAllBooksByCategory(@PathVariable Long categoryId){
        return bookResponse.getAllBooksByCategory(categoryId);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody Book book,@PathVariable Long bookId){
        return bookResponse.updateBook(book, bookId);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId){
        return bookResponse.deleteBook(bookId);
    }



}
