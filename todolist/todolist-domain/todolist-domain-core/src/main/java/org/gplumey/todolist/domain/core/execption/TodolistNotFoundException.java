package org.gplumey.todolist.domain.core.execption;

import org.gplumey.common.domain.core.entity.BaseId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

public class TodolistNotFoundException extends TodolistDomainException {
    public TodolistNotFoundException(TodolistId todolistId) {
        super(String.format("Todolist %s cannot be found.", BaseId.nullSafeValue(todolistId)));
    }
}
