package org.gplumey.todolist.infra.messaging;

import lombok.extern.slf4j.Slf4j;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.todolist.domain.service.eventing.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Qualifier(DomainEventPublisher.BROKER)
public class PubSubDomainEventPublisher implements DomainEventPublisher {
    @Override
    public void publish(DomainEvent event) {
        log.info("TodolistEventPublisher " + AvroModelMapper.adaptor(event));
    }
}
