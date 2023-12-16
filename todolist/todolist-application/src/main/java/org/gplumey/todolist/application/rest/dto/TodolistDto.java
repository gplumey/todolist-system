package org.gplumey.todolist.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.gplumey.todolist.application.rest.TodolistController;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
@AllArgsConstructor
@Getter
public class TodolistDto extends RepresentationModel<TodolistDto> {
    private final String id;
    private final String name;
    private final Collection<TaskDto> tasks;

    public static TodolistDto of(Todolist domain) {
        TodolistDto dto = TodolistDto.builder()
                                     .id(domain.getId().getValue().toString())
                                     .name(domain.getName().getValue())
                                     .tasks(domain.getTasks().stream().map(TaskDto::of).toList())
                                     .build();

        dto.add(linkTo(methodOn(TodolistController.class).get(domain.getId().getValue())).withSelfRel());
        dto.add(linkTo(methodOn(TodolistController.class).postTask(domain.getId().getValue(), null)).withRel("add-task"));

        return dto;
    }
}
