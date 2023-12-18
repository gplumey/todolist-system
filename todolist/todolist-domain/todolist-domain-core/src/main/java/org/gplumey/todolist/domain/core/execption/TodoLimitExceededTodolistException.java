package org.gplumey.todolist.domain.core.execption;

import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

public class TodoLimitExceededTodolistException extends TodolistDomainException {
    public TodoLimitExceededTodolistException(TodolistId todolistId, int limit) {
        super(String.format("You have reached the limit on the number of todo you can create (%d) in the todolist %s", limit, todolistId.getValue()));
    }

}
