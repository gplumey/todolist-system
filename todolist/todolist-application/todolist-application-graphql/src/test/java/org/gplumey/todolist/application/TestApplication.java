package org.gplumey.todolist.application;

import io.micrometer.observation.ObservationRegistry;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;


@SpringBootApplication(scanBasePackages = "org.gplumey.todolist")
@AutoConfigureGraphQlTester

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
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }
}
