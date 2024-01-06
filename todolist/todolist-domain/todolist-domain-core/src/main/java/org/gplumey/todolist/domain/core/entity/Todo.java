package org.gplumey.todolist.domain.core.entity;

import lombok.Builder;
import lombok.Getter;
import org.gplumey.common.domain.core.entity.BaseEntity;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoLabel;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

import java.util.Objects;


@Getter
public class Todo extends BaseEntity<TodoId> {

    private final TodolistId todolistId;
    private final TodoLabel label;

    @Builder
    public Todo(TodolistId todolistId, TodoId id, TodoLabel label) {
        super(id);
        this.todolistId = Objects.requireNonNull(todolistId);
        this.label = Objects.requireNonNull(label);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
