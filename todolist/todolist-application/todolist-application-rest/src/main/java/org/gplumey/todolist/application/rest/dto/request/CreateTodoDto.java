package org.gplumey.todolist.application.rest.dto.request;

import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;

import java.util.UUID;

public record CreateTodoDto(String label) {


    public static CreateTodoCommand adaptor(UUID todolistId, CreateTodoDto dto) {
        return new CreateTodoCommand() {

            @Override
            public TodolistId getTodolistId() {
                return TodolistId.of(todolistId);
            }

            @Override
            public String getLabel() {
                return dto.label();
            }
        };
    }
}
