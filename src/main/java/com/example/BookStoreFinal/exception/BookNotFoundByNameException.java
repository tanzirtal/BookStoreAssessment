package com.example.BookStoreFinal.exception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundByNameException extends EntityNotFoundException {
    public BookNotFoundByNameException() {
    }

    public BookNotFoundByNameException(String message) {
        super(message);
    }
}
