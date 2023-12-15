package org.gplumey.todolist.domain.core.entity;


import lombok.Builder;
import lombok.Getter;
import org.gplumey.common.domain.core.entity.AggregateRoot;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;


@Getter
public class Todolist extends AggregateRoot<TodolistId> {

    private TodolistName name;

    @Builder
    public Todolist(TodolistId id, TodolistName name) {
        super(id);
        this.name = name;
    }

    public static Todolist create(String name) {
        return Todolist.builder()
                       .id(TodolistId.create())
                       .name(TodolistName.of(name))
                       .build();
    }
}
