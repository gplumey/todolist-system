package org.gplumey.todolist.domain.core.event;

import org.gplumey.todolist.domain.core.entity.Todolist;

public class TodoListCreatedEvent extends TodolistEvent {
    public TodoListCreatedEvent(Todolist source) {
        super(source);
    }

    @Override
    public EventType getEventType() {
        return TodolistEventType.TODOLIST_CREATE;
    }
}
