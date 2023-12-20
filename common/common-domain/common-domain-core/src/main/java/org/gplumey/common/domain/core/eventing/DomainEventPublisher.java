package org.gplumey.common.domain.core.eventing;

public interface DomainEventPublisher {


    void publishDomainEvent(DomainEvent event);
}
