package org.gplumey.todolist.domain.core.event;

import org.gplumey.todolist.domain.core.entity.Todolist;

public class TodolistDeletedEvent extends TodolistEvent {


    public TodolistDeletedEvent(Todolist source) {
        super(source);
    }

    @Override
    public EventType getEventType() {
        return Types.TODOLIST_DELETE;
    }

    @Override
    public DispathMode getDispathMode() {
        return DispathMode.NON_TRANSACTIONAL;
    }
}
