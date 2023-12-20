package org.gplumey.common.domain.core.eventing;

import org.gplumey.common.domain.core.util.Assert;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent<T> {

    private final Instant createAt;
    private final UUID id;
    private final T source;

    public DomainEvent(T source) {
        this.source = Assert.notNull(source, "source must not be null");
        this.createAt = Instant.now();
        this.id = UUID.randomUUID();
    }

    public T getSource() {
        return source;
    }


    public abstract EventType getEventType();

    public String getName() {
        return getEventType().getName();
    }

    public interface EventType {
        String getName();
    }
}
