package org.gplumey.todolist.domain.service.outbox;

import org.gplumey.todolist.domain.core.outbox.OutboxMessage;

import java.util.Collection;

public interface OutboxMessageRepository {

    void create(OutboxMessage message);

    void update(OutboxMessage message);

    void delete(OutboxMessage message);

    Collection<OutboxMessage> getMessagesToPublish();
}
