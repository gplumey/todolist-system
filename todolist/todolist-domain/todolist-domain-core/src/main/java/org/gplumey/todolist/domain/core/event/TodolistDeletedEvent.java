package org.gplumey.todolist.domain.core.event;

import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;

public class TodoDeletedEvent extends TodolistEvent {

    private final Todo todo;

    public TodoDeletedEvent(Todolist source, Todo newTodo) {
        super(source);
        this.todo = newTodo;
    }

    public Todo getTodo() {
        return todo;
    }

    @Override
    public EventType getEventType() {
        return Types.TODO_CREATE;
    }

    @Override
    public DispathMode getDispathMode() {
        return DispathMode.NON_TRANSACTIONAL;
    }
}
