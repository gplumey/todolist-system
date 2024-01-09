package org.gplumey.common.domain.core.entity;

import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.common.domain.core.eventing.DomainEventDispatcher;

import java.util.LinkedList;
import java.util.Optional;

public class AggregateRoot<T, E extends DomainEvent> extends BaseEntity<T> {
    private final LinkedList<E> domainEvents = new LinkedList<>();

    protected AggregateRoot(T id) {
        super(id);
    }


    protected AggregateRoot addDomainEvent(E domainEvent) {
        domainEvents.add(domainEvent);
        return this;
    }

    public void fireEvents(DomainEventDispatcher dispatcher) {
        Optional.of(domainEvents).ifPresent(events -> events.forEach(dispatcher::dispatch));
        domainEvents.clear();
    }
}
