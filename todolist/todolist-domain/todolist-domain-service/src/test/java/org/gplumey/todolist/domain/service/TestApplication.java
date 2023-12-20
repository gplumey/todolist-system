package org.gplumey.todolist.domain.service;


import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.gplumey.todolist.domain.service.port.output.TodolistEventPublisher;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    TodolistReadRepository todolistReadRepository() {
        return Mockito.mock(TodolistReadRepository.class);
    }

    @Bean
    TodolistWriteRepository todolistWriteRepository() {
        return Mockito.mock(TodolistWriteRepository.class);
    }

    @Bean
    TodolistEventPublisher todolistEventPublisher() {
        return Mockito.mock(TodolistEventPublisher.class);
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
        return Mockito.mock(ApplicationEventPublisher.class);
    }
}
