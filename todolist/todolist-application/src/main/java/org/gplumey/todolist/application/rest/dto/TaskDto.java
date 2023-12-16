package org.gplumey.todolist.application.rest.dto;

import lombok.Builder;
import org.gplumey.todolist.domain.core.entity.Task;

import java.util.UUID;

@Builder
public record TaskDto(UUID id, String name) {

    public static TaskDto of(Task task) {
        return TaskDto.builder().id(task.getId().getValue()).name(task.getLabel().getValue()).build();
    }
}
