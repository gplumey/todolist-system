package org.gplumey.todolist.domain.core.event;

import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.common.domain.core.outbox.OutboxMessagePayload;
import org.gplumey.todolist.domain.core.entity.Todolist;

public abstract class TodolistEvent extends DomainEvent<Todolist> implements OutboxMessagePayload {

    public TodolistEvent(Todolist source) {
        super(source);
    }
}
