package org.gplumey.common.domain.core.eventing;

import org.gplumey.common.domain.core.outbox.OutboxMessagePayload;
import org.gplumey.common.domain.core.util.Assert;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent<T> implements OutboxMessagePayload {

    private final Instant createdAt;
    private final UUID id;
    private final T source;

    public DomainEvent(T source) {
        this.source = Assert.notNull(source, "source must not be null");
        this.createdAt = Instant.now();
        this.id = UUID.randomUUID();
    }

    public abstract DispathMode getDispathMode();

    public T getSource() {
        return source;
    }

    public abstract EventType getEventType();

    public String getName() {
        return getEventType().getName();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UUID getId() {
        return id;
    }

    public enum Types implements EventType {
        TODOLIST_CREATE("todolist.create.event"),
        TODO_CREATE("todolist.todo.create.event");


        private final String name;


        Types(String name) {
            this.name = name;
        }


        @Override
        public String getName() {
            return name;
        }
    }

    public enum DispathMode {
        TRANSACTIONAL, NON_TRANSACTIONAL
    }


    public interface EventType {
        String getName();
    }
}
