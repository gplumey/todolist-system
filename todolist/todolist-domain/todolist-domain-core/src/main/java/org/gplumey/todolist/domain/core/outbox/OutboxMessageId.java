package org.gplumey.todolist.domain.core.outbox;

import org.gplumey.common.domain.core.entity.BaseId;

import java.util.UUID;

public class OutboxMessageId extends BaseId<UUID> {
    protected OutboxMessageId(UUID value) {
        super(value);
    }

    public static OutboxMessageId create() {
        return new OutboxMessageId(UUID.randomUUID());
    }
}
