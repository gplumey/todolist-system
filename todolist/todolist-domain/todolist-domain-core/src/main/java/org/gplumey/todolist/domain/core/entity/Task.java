package org.gplumey.todolist.domain.core.entity;

import lombok.Builder;
import lombok.Getter;
import org.gplumey.common.domain.core.entity.BaseEntity;
import org.gplumey.todolist.domain.core.entity.valueobject.TaskId;
import org.gplumey.todolist.domain.core.entity.valueobject.TaskLabel;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

import java.util.Objects;


@Getter
public class Task extends BaseEntity<TaskId> {

    private final TodolistId;
    private TaskLabel label;


    @Builder
    public Task(TodolistId todolistId, TaskId id, TaskLabel label) {
        super(id);
        this.label = Objects.requireNonNull(label);
    }
}
