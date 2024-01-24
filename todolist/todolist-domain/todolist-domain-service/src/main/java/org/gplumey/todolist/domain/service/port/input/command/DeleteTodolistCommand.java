package org.gplumey.todolist.domain.service.port.input.command;

import jakarta.validation.constraints.NotBlank;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;


public interface DeleteTodolistCommand {

    @NotBlank
    TodolistId getTodolistId();
}
