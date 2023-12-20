package org.gplumey.todolist.domain.core.event;

import lombok.Getter;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.todolist.domain.core.entity.Todolist;

public abstract class TodolistEvent extends DomainEvent<Todolist> {

    public TodolistEvent(Todolist source) {
        super(source);
    }


    @Getter
    public enum TodolistEventType implements EventType {
        TODOLIST_CREATE("todolist.create.event"),
        TODO_CREATE("todolist.todo.create.event");


        private final String name;

        TodolistEventType(String name) {
            this.name = name;
        }
    }
}
