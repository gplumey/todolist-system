package org.gplumey.todolist.domain.service;


import org.gplumey.todolist.domain.service.port.output.TodolistRepository;
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
     TodolistRepository todolistRepository() {
        return Mockito.mock(TodolistRepository.class);
    }



}
