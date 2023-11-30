package com.example.BookStoreFinal.repository;

import com.example.BookStoreFinal.model.Book;
import com.example.BookStoreFinal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name = ?1")
    Optional<Book> getBookByName(String name);

    @Query("SELECT b FROM Book b WHERE b.sku = ?1")
    Optional<Book> getBookBySku(Long sku);

    List<Book> findByName(String name);
    List<Book> findBySku(Long sku);

    List<Book> findByCategory(Category category);
}
