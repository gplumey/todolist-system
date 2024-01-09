package org.gplumey.todolist.domain.service;

import io.cucumber.spring.CucumberContextConfiguration;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.mockito.Mockito.mock;

@CucumberContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(classes = TestConfig.class)
@RecordApplicationEvents
public class CucumberSpringConfiguration {


    @Bean
    TodolistReadRepository todolistReadRepository() {
        return mock(TodolistReadRepository.class);
    }

    @Bean
    TodolistWriteRepository todolistWriteRepository() {
        return mock(TodolistWriteRepository.class);
    }
}
