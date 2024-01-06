package org.gplumey.todolist.domain.core.event;

import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.common.domain.core.outbox.OutboxMessagePayload;
import org.gplumey.todolist.domain.core.entity.Todo;

public abstract class TodoEvent extends DomainEvent<Todo> implements OutboxMessagePayload {

    public TodoEvent(Todo source) {
        super(source);
    }
}
