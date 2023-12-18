package org.gplumey.todolist.application.rest.dto.request;

import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;

public record CreateTodoDto(String label) {


    public static CreateTodoCommand toCommand(CreateTodoDto dto) {
        return new CreateTodoCommand(TodolistId.of(dto.label), dto.label);
    }
}
