package org.gplumey.todolist.domain.service.port.input.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

public record AddTaskCommand(@NotNull TodolistId todolistId, @NotBlank String taskname) {
}