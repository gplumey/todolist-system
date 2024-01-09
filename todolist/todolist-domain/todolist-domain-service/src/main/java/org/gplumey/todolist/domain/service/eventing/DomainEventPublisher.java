package org.gplumey.todolist.domain.service.eventing;

import org.gplumey.common.domain.core.eventing.DomainEvent;

public interface DomainEventPublisher {
    String OUTBOX = "outbox-publisher";
    String BROKER = "broker-publisher";

    void publish(DomainEvent event);
}
