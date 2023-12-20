package org.gplumey.todolist.domain.service.eventing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.common.domain.core.eventing.DomainEventPublisher;
import org.gplumey.todolist.domain.core.event.TodolistEvent;
import org.gplumey.todolist.domain.service.port.output.TodolistEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class LoggingDomainEventDispatcher implements DomainEventPublisher {
    //  private ApplicationEventPublisher publisher;


    private final TodolistEventPublisher todolistEventPublisher;

    @Override
    public void publishDomainEvent(DomainEvent event) {
        log.info("Publish event" + event.getClass().getSimpleName());
        if (event instanceof TodolistEvent) {
            todolistEventPublisher.publishDomainEvent((TodolistEvent) event);
        }
    }
}
