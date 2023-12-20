package org.gplumey.todolist.domain.service.port.output;

import org.gplumey.todolist.domain.core.event.TodolistEvent;

public interface TodolistEventPublisher {
    //  private ApplicationEventPublisher publisher;

    void publishDomainEvent(TodolistEvent event);
}
