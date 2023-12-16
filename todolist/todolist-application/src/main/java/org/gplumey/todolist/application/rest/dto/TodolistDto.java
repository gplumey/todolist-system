package org.gplumey.todolist.application.rest.dto;

import lombok.Builder;
import org.gplumey.todolist.domain.core.entity.Todolist;

import java.util.Collection;

@Builder
public record TodolistDto(String id, String name, Collection<TaskDto> tasks) {

    public static TodolistDto of(Todolist domain) {
        return TodolistDto.builder()
                          .id(domain.getId().getValue().toString())
                          .name(domain.getName().getValue())
                          .tasks(domain.getTasks().stream().map(TaskDto::of).toList())
                          .build();
    }
}
