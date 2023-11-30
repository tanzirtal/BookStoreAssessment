package com.example.BookStoreFinal.service;

import com.example.BookStoreFinal.exception.BookNotFoundByCategoryException;
import com.example.BookStoreFinal.exception.BookNotFoundByIdException;
import com.example.BookStoreFinal.exception.BookNotFoundByNameException;
import com.example.BookStoreFinal.model.Book;
import com.example.BookStoreFinal.model.Category;
import com.example.BookStoreFinal.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    public Book createBook(Book book) {

        book.setCategory(book.getCategory());
        book.setDescription(book.getDescription());
        book.setName(book.getName());
        book.setSku(book.getSku());
        book.setImage(book.getImage());
        book.setPrice(book.getPrice());
        book.setStock(book.getStock());

        logger.info("Book successfully created");
        return bookRepository.save(book);
    }

    public Book getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> {
            logger.error("Book with Id: " + bookId + " not found.");
            throw new BookNotFoundByIdException("Book with Id: " + bookId + " not found.");
        });

        logger.info("Book successfully retrieved");
        return book;
    }

    public List<Book> getAllBooks(String name, Long sku) {
        //null and empty check for name string when put in as request params
        if (name != null && !name.isEmpty()) {
            return bookRepository.findByName(name);
            //null check for sku
        } else if (sku != null) {
            return bookRepository.findBySku(sku);
        } else {
            return bookRepository.findAll();
        }
    }

//    public Iterable<Book> getAllBooks() {
//        List<Book> books = bookRepository.findAll();
//        if (books.isEmpty()) {
//            logger.error("List of books is empty");
//            throw new BookNotFoundByIdException("List of books is empty");
//        }
//        logger.info("All books successfully retreieved");
//
//        return bookRepository.findAll();
//    }


//    public Book getBookByName(String name) {
//        Optional<Book> bookOptional = bookRepository.getBookByName(name);
//
//        if (bookOptional.isPresent()) {
//            Book book = bookOptional.get();
//            logger.info("Book " + name + " successfully retrieved");
//            return book;
//        } else {
//            logger.error("Could not find book " + name);
//            throw new BookNotFoundByNameException("Could not find book " + name);
//        }
//    }
//
//    public Book getBookBySku(Long sku) {
//        Optional<Book> bookOptional = bookRepository.getBookBySku(sku);
//
//        if (bookOptional.isPresent()) {
//            Book book = bookOptional.get();
//            logger.info("Book " + sku + " successfully retrieved");
//            return book;
//        } else {
//            logger.error("Could not find book " + sku);
//            throw new BookNotFoundByNameException("Could not find book " + sku);
//        }
//    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = getBookById(bookId);

        existingBook.setStock(updatedBook.getStock());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setName(updatedBook.getName());
        existingBook.setSku(updatedBook.getSku());
        existingBook.setImage(updatedBook.getImage());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setCategory(updatedBook.getCategory());

        logger.info("Book information successfully updated");
        bookRepository.save(existingBook);
        return existingBook;

    }

    public void deleteBook(Long bookId) {
        Book bookToDelete = getBookById(bookId);
        logger.info("Book successfully deleted");
        bookRepository.delete(bookToDelete);
    }

    public List<Book> getAllBooksByCategory(Long categoryId) {

        Category category = new Category();
        category.setId(categoryId);

        List<Book> books = bookRepository.findByCategory(category);

        if (books.isEmpty()) {
            String errorMessage = "No books exist with a category id of: " + categoryId;
            logger.info(errorMessage);
            throw new BookNotFoundByNameException(errorMessage);
        }

        return books;

    }
}
