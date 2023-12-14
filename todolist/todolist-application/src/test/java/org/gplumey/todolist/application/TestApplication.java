package org.gplumey.todolist.application;

import org.gplumey.todolist.domain.service.port.input.TodolistService;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public TodolistService todolistService() {
        return Mockito.mock(TodolistService.class);
    }
}
