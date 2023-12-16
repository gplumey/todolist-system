package org.gplumey.todolist.domain.service.port.input.query;

import jakarta.validation.constraints.NotNull;
import org.gplumey.todolist.domain.core.entity.valueobject.TaskId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

public record GetTaskQuery(@NotNull TodolistId todolistId, @NotNull TaskId taskId) {
}
