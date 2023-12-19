package org.gplumey.todolist.domain.service.eventing;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.entity.AggregateRoot;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Publisher {
    private final ApplicationEventPublisher publisher;

    private void publishEvent(DomainEvent event) {
        publisher.publishEvent(event);
    }

    public void publishEvents(AggregateRoot aggregateRoot) {
        List<DomainEvent> events = aggregateRoot.getDomainEvents();
        events.forEach(event -> this.publishEvent(event));
    }
}
