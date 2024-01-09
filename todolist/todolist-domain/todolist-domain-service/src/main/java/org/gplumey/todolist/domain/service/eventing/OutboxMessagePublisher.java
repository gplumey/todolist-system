package org.gplumey.todolist.domain.service.eventing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.todolist.domain.core.outbox.OutboxMessage;
import org.gplumey.todolist.domain.service.outbox.OutboxMessageRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Qualifier(DomainEventPublisher.OUTBOX)
@Slf4j
@AllArgsConstructor
public class OutboxMessagePublisher implements DomainEventPublisher {

    private final OutboxMessageRepository repository;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void publish(DomainEvent event) {
        OutboxMessage message = OutboxMessage.create(event);
        repository.create(message);
    }


    @Scheduled(fixedRate = 3000)
    public void poll() {
        log.info("POOLING ");

        repository.getMessagesToPublish().forEach(outboxMessage -> {
                    log.info("POOLED " + outboxMessage.getPayload());
                    domainEventPublisher.publish((DomainEvent) outboxMessage.getPayload());
                    repository.delete(outboxMessage);
                }
        );
    }
}
