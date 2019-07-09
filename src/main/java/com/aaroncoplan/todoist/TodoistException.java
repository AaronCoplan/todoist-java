package com.aaroncoplan.todoist;

public class TodoistException extends Exception {

    public TodoistException(int httpStatus) {
        super(String.format("Request failed with HTTP Status %d", httpStatus));
    }

    public TodoistException(String message) {
        super(message);
    }
}
