package org.gplumey.todolist.application.rest.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.gplumey.todolist.application.rest.TodoRestController;
import org.gplumey.todolist.application.rest.TodolistRestController;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
@Getter
@AllArgsConstructor
public class TodoResource extends RepresentationModel<TodolistResource> {
    private final UUID id;
    private final String label;

    public static TodoResource of(Todo todo) {
        TodoResource dto = TodoResource.builder().id(todo.getId().getValue()).label(todo.getLabel().getValue()).build();

        dto.add(linkTo(methodOn(TodoRestController.class).get(todo.getTodolistId().getValue(),
                todo.getId().getValue())).withSelfRel());
        dto.add(linkTo(methodOn(TodolistRestController.class).getById(todo.getTodolistId().getValue())).withRel("todolist"));
        return dto;
    }
}
