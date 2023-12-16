package org.gplumey.todolist.domain.service.port.input.query;

import jakarta.validation.constraints.NotNull;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

public record GetTodolistQuery(@NotNull TodolistId todolistId) {
}
