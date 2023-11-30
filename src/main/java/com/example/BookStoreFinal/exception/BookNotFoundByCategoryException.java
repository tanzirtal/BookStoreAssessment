package com.example.BookStoreFinal.exception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundByCategoryException extends EntityNotFoundException {
    public BookNotFoundByCategoryException() {
    }

    public BookNotFoundByCategoryException(String message) {
        super(message);
    }
}
