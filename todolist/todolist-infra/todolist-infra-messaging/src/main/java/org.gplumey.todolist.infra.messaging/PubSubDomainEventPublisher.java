package org.gplumey.todolist.infra.messaging;

import lombok.extern.slf4j.Slf4j;
import org.gplumey.todolist.domain.core.event.TodolistEvent;
import org.gplumey.todolist.domain.service.port.output.TodolistEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PubSubDomainEventPublisher implements TodolistEventPublisher {
    @Override
    public void publishDomainEvent(TodolistEvent event) {
        log.info("TodolistEventPublisher " + AvroModelMapper.adaptor(event));
    }
}
