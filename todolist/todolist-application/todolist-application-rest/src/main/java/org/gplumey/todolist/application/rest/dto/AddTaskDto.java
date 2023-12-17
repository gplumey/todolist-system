package org.gplumey.todolist.application.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record AddTaskDto (@NotBlank String label){


}
