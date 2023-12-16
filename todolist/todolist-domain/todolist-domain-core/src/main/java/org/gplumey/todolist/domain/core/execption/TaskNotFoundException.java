package org.gplumey.todolist.domain.core.execption;

import org.gplumey.todolist.domain.core.entity.valueobject.TaskId;

public class TaskNotFoundException extends TodolistDomainException {
    public TaskNotFoundException(TaskId taskId) {
        super(String.format("Task %s cannot be found.", TaskId.nullSafeValue(taskId)));
    }
}
