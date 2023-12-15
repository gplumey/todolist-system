package org.gplumey.todolist.domain.service.port.input;

import jakarta.validation.constraints.NotBlank;

public record CreateTodolistCommand(@NotBlank String name) {
}
