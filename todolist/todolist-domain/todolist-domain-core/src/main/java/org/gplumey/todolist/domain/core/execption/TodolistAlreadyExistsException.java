package org.gplumey.todolist.domain.core.execption;

import org.gplumey.common.domain.core.exception.DomainException;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;

public class TodolistAlreadyExistsException extends DomainException {
    public TodolistAlreadyExistsException(TodolistName todolistName) {
        super("Todolist with name '%s' already exists".formatted(todolistName.value()));
    }
}
