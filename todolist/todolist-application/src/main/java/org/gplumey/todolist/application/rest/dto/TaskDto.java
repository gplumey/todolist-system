package org.gplumey.todolist.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.gplumey.todolist.application.rest.TodolistController;
import org.gplumey.todolist.domain.core.entity.Task;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class TaskDto extends RepresentationModel<TodolistDto> {
    private final UUID id;
    private final String label;

    public static TaskDto of(Task task) {
        TaskDto dto = TaskDto.builder().id(task.getId().getValue()).label(task.getLabel().getValue()).build();
        dto.add(linkTo(methodOn(TodolistController.class).getTask(task.getTodolistId().getValue(), task.getId().getValue())).withSelfRel());
        dto.add(linkTo(methodOn(TodolistController.class).get(task.getTodolistId().getValue())).withRel("todolist"));
        return dto;
    }
}
