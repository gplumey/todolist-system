package org.gplumey.todolist.application.rest.dto;

import lombok.Builder;
import org.gplumey.todolist.domain.core.entity.Todolist;

@Builder
public record TodolistDto(String id, String name) {

    public static TodolistDto of(Todolist domain) {
        return TodolistDto.builder()
                          .id(domain.getId().getValue().toString())
                          .name(domain.getName().getValue())
                          .build();
    }
}
