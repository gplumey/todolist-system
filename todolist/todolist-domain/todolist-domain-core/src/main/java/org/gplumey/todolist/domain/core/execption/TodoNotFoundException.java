package org.gplumey.todolist.domain.core.execption;

import org.gplumey.common.domain.core.entity.BaseId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoId;

public class TodoNotFoundException extends TodolistDomainException {
    public TodoNotFoundException(TodoId todoId) {
        super(String.format("Todo %s cannot be found.", BaseId.nullSafeValue(todoId)));
    }
}
