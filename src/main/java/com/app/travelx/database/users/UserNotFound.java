package com.app.travelx.database.users;

public class UserNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UserNotFound(String msg) {
        super(msg);
    }
}