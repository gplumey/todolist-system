package org.gplumey.todolist.domain.core.event;

import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;

public class TodoCreatedEvent extends TodolistEvent {

    private final Todo todo;

    public TodoCreatedEvent(Todolist source, Todo newTodo) {
        super(source);
        this.todo = newTodo;
    }

    @Override
    public EventType getEventType() {
        return TodolistEventType.TODO_CREATE;
    }
}
