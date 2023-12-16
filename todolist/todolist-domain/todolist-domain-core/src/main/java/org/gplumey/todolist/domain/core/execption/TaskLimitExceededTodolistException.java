package org.gplumey.todolist.domain.core.execption;

public class TaskLimitExceededTodolistException extends TodolistDomainException {
    public TaskLimitExceededTodolistException(int limit) {
        super(String.format("You have reached the limit on the number of task you can create (%d)", limit));
    }

    public TaskLimitExceededTodolistException(String message, Throwable cause) {
        super(message, cause);
    }
}
