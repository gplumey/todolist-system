package org.gplumey.todolist.domain.core.outbox;

import lombok.Builder;
import lombok.Getter;
import org.gplumey.common.domain.core.entity.AggregateRoot;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.common.domain.core.outbox.OutboxMessagePayload;

import java.time.Instant;

@Getter
public class OutboxMessage extends AggregateRoot<OutboxMessageId, DomainEvent> {

    private final Instant createdAt;
    private final OutboxMessageState state;

    private final OutboxMessagePayload payload;

    @Builder
    protected OutboxMessage(OutboxMessageId id, Instant createdAt, OutboxMessageState state, OutboxMessagePayload payload) {
        super(id);
        this.createdAt = createdAt;
        this.state = state;
        this.payload = payload;
    }

    public static OutboxMessage create(OutboxMessagePayload payload) {
        return new OutboxMessage(OutboxMessageId.create(), payload.getCreatedAt(), OutboxMessageState.STARTED, payload);
    }

    public enum OutboxMessageState {
        STARTED, COMPLETED, FAILED
    }
}
