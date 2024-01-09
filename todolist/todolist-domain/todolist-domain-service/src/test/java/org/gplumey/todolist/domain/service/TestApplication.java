package org.gplumey.todolist.domain.service;


import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.gplumey.todolist.domain.service.eventing.DomainEventPublisher;
import org.gplumey.todolist.domain.service.outbox.OutboxMessageRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    TodolistReadRepository todolistReadRepository() {
        return mock(TodolistReadRepository.class);
    }

    @Bean
    TodolistWriteRepository todolistWriteRepository() {
        return mock(TodolistWriteRepository.class);
    }

    @Bean
    @Qualifier(DomainEventPublisher.BROKER)
    DomainEventPublisher domainEventPublisher() {
        return mock(DomainEventPublisher.class);
    }

    @Bean
    OutboxMessageRepository outboxMessageRepository() {
        return mock(OutboxMessageRepository.class);
    }


    @Bean
    ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }

    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    ApplicationEventPublisher eventPublisher() {
        return mock(ApplicationEventPublisher.class);
    }
}
