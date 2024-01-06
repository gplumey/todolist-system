package org.gplumey.todolist.domain.service.eventing;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.common.domain.core.eventing.DomainEventDispatcher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Observed
public class DomainEventDispatcherImpl implements DomainEventDispatcher {

    private final DomainEventPublisher pubSubDomainEventPublisher;
    private final DomainEventPublisher outboxMessagePublisher;

    public DomainEventDispatcherImpl(@Qualifier(DomainEventPublisher.BROKER) DomainEventPublisher pubSubDomainEventPublisher,
                                     @Qualifier(DomainEventPublisher.OUTBOX) DomainEventPublisher outboxMessagePublisher) {
        this.pubSubDomainEventPublisher = pubSubDomainEventPublisher;
        this.outboxMessagePublisher = outboxMessagePublisher;
    }

    @Override
    public void dispatch(DomainEvent event) {
        switch (event.getDispathMode()) {
            case TRANSACTIONAL:
                outboxMessagePublisher.publish(event);
                break;
            case NON_TRANSACTIONAL:
                pubSubDomainEventPublisher.publish(event);
                break;
            default:
                throw new IllegalArgumentException("Dispatch mode not provided for " + event.getClass().getSimpleName());
        }
    }
}
