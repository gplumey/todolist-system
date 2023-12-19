package org.gplumey.todolist.domain.core.execption;

import org.gplumey.common.domain.core.exception.DomainException;

public class TodolistDomainException extends DomainException {
    public TodolistDomainException(String message) {
        super(message);
    }
}
