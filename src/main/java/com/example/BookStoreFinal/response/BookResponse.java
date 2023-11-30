package com.example.BookStoreFinal.response;

import com.example.BookStoreFinal.dto.Detail;
import com.example.BookStoreFinal.model.Book;
import com.example.BookStoreFinal.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class BookResponse {

    @Autowired
    BookService bookService;

    public ResponseEntity<?> createBook(Book book){
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newDepositURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bookId}").buildAndExpand(book.getId()).toUri();
        responseHeaders.setLocation(newDepositURI);

        Detail detail = new Detail();
        detail.setData(bookService.createBook(book));
        detail.setCode(HttpStatus.CREATED.value());
        detail.setMessage("Book successfully created for " + book.getName());

        return new ResponseEntity<>(detail, responseHeaders, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getBookById(Long bookId){

        Detail detail = new Detail();
        detail.setData(bookService.getBookById(bookId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("Book successfully retrieved for id: " + bookId);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

//    public ResponseEntity<?> getBookBySku(Long sku){
//
//        Detail detail = new Detail();
//        detail.setData(bookService.getBookBySku(sku));
//        detail.setCode(HttpStatus.OK.value());
//        detail.setMessage("Book successfully retrieved for SKU:" + sku);
//
//        return new ResponseEntity<>(detail, HttpStatus.OK);
//    }
//
//    public ResponseEntity<?> getBookByName(String name){
//
//        Detail detail = new Detail();
//        detail.setData(bookService.getBookByName(name));
//        detail.setCode(HttpStatus.OK.value());
//        detail.setMessage("Book successfully retrieved for SKU:" + name);
//
//        return new ResponseEntity<>(detail, HttpStatus.OK);
//    }

    public ResponseEntity<?> getAllBooks(String name, Long sku){

        Detail detail = new Detail();
        detail.setData(bookService.getAllBooks(name, sku));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("All books successfully retrieved");

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllBooksByCategory(Long categoryId){

        Detail detail = new Detail();
        detail.setData(bookService.getAllBooksByCategory(categoryId));
        detail.setCode(HttpStatus.OK.value());
        detail.setMessage("All books successfully retrieved for category id: " + categoryId);

        return new ResponseEntity<>(detail, HttpStatus.OK);
    }

    public ResponseEntity<?> updateBook(Book book, Long bookId){

        Detail detail = new Detail();
        detail.setData(bookService.updateBook(bookId, book));
        detail.setCode(HttpStatus.ACCEPTED.value());
        detail.setMessage("Book successfully updated");

        return new ResponseEntity<>(detail, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteBook(Long bookId){

        Detail detail = new Detail();
        detail.setMessage("Book successfully deleted.");
        bookService.deleteBook(bookId);
        detail.setCode(HttpStatus.NO_CONTENT.value());


        return new ResponseEntity<>(detail, HttpStatus.NO_CONTENT);
    }

}
