package org.gplumey.todolist.application.rest.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.gplumey.todolist.application.rest.TodoRestController;
import org.gplumey.todolist.application.rest.TodolistRestController;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
@AllArgsConstructor
@Getter
public class TodolistResource extends RepresentationModel<TodolistResource> {
    private final String id;
    private final String name;
    private final Collection<TodoResource> todos;

    public static TodolistResource of(Todolist domain) {
        TodolistResource dto = TodolistResource.builder()
                                               .id(domain.getId().getValue().toString())
                                               .name(domain.getName().getValue())
                                               .todos(domain.getTodos().stream().map(TodoResource::of).toList())
                                               .build();

        dto.add(linkTo(methodOn(TodolistRestController.class).getById(domain.getId().getValue())).withSelfRel());
        dto.add(linkTo(methodOn(TodoRestController.class).post(domain.getId().getValue(), null)).withRel("add-task"));

        return dto;
    }
}
