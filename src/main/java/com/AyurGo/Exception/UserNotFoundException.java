package com.AyurGo.Exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}