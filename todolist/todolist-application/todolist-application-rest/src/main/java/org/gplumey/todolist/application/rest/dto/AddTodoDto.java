package org.gplumey.todolist.application.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record AddTodoDto(@NotBlank String label){


}
