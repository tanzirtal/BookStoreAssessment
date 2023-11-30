package com.example.BookStoreFinal.exception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundByIdException extends EntityNotFoundException {
    public BookNotFoundByIdException() {
    }

    public BookNotFoundByIdException(String message) {
        super(message);
    }
}
