package com.revature.phuflix.util.custom_exception;

public class UserInputException extends RuntimeException{
    public UserInputException(String message) {
        super(message);
    }
}
