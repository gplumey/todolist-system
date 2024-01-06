package org.gplumey.common.domain.core.eventing;

public interface DomainEventDispatcher {


    void dispatch(DomainEvent event);
}
