package org.gplumey.todolist.domain.service.port.usecase.create;

import jakarta.validation.ConstraintViolationException;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.input.CreateTodolistUserCase;
import org.gplumey.todolist.domain.service.port.output.TodolistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class CreateTodolistUserCaseTest {


    @Autowired
    CreateTodolistUserCase createTodolistUserCase;

    @Autowired
    TodolistRepository todolistRepository;

    private static Stream<Arguments> should_create_todolist_given_valid_request() {
        return Stream.of(Arguments.of("My first todolist"));
    }

    @BeforeEach
    void setup() {
        reset(todolistRepository);
        LocaleContextHolder.setLocale(Locale.US);
        when(todolistRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    @ParameterizedTest
    @MethodSource
    void should_create_todolist_given_valid_request(String name) {
        Todolist todolist = createTodolistUserCase.execute(new CreateTodolistCommand(name));
        assertEquals(name, todolist.getName().getValue());
        verify(todolistRepository).save(any());
    }

    @Test
    void should_fail_when_name_is_null() {
        Executable exec = () -> createTodolistUserCase.execute(new CreateTodolistCommand(null));
        Throwable thrown = assertThrows(ConstraintViolationException.class, exec);
        assertEquals("name: must not be blank", thrown.getMessage());
        verify(todolistRepository, never()).save(any());
    }
}