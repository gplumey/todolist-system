package org.gplumey.todolist.dataaccess;

import lombok.extern.slf4j.Slf4j;
import org.gplumey.todolist.domain.core.outbox.OutboxMessage;
import org.gplumey.todolist.domain.core.outbox.OutboxMessageId;
import org.gplumey.todolist.domain.service.outbox.OutboxMessageRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class OutboxMessageRepositoryImpl implements OutboxMessageRepository {


    static ConcurrentHashMap<OutboxMessageId, OutboxMessage> map = new ConcurrentHashMap<>();


    @Override
    public void create(OutboxMessage message) {
        map.put(message.getId(), message);
        log.info(map.values().stream().map(outboxMessage -> outboxMessage.getId().toString()).collect(Collectors.joining(",")));
    }

    @Override
    public void update(OutboxMessage message) {
        map.computeIfPresent(message.getId(), (outboxMessageId, outboxMessage) -> {
            throw new IllegalStateException("Message with id " + outboxMessageId + " not found");
        });
    }

    @Override
    public void delete(OutboxMessage message) {
        map.remove(message.getId());
    }


    @Override
    public Collection<OutboxMessage> getMessagesToPublish() {
        return map.values().stream().filter(outboxMessage -> outboxMessage.getState() == OutboxMessage.OutboxMessageState.STARTED).toList();
    }
}
