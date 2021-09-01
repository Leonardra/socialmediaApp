package com.thecommunity.exceptions;

public class FriendsNotFoundException extends RuntimeException {
    public FriendsNotFoundException() {
        super();
    }

    public FriendsNotFoundException(String message) {
        super(message);
    }

    public FriendsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FriendsNotFoundException(Throwable cause) {
        super(cause);
    }
}
