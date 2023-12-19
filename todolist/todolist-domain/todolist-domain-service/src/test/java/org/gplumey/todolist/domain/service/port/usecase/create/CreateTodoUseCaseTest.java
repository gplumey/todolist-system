package org.gplumey.todolist.domain.service.port.usecase.create;

import jakarta.validation.ConstraintViolationException;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.CreateTodoUseCase;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
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
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class CreateTodoUseCaseTest {


    @Autowired
    CreateTodoUseCase useCase;

    @Autowired
    TodolistWriteRepository writeRepository;

    @Autowired
    TodolistReadRepository readRepository;
    TodolistId todolistId = TodolistId.of("095d6c0c-69bc-48f8-b139-d3e06194cdc5");
    Todolist todolist = Todolist.builder().id(todolistId).name(TodolistName.of("Test todolist")).build();

    private static Stream<Arguments> should_add_todo_given_valid_request() {
        return Stream.of(Arguments.of("My first todolist"));
    }

    @BeforeEach
    void setup() {
        reset(writeRepository, readRepository);
        LocaleContextHolder.setLocale(Locale.US);
        when(writeRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(readRepository.get(todolistId)).thenReturn(Optional.of(todolist));
    }

    @ParameterizedTest
    @MethodSource
    void should_add_todo_given_valid_request(String name) {
        Todo task = useCase.execute(new CreateTodoCommand(todolistId, name));
        assertEquals(name, task.getLabel().getValue());
        verify(writeRepository).save(any());
    }

    @Test
    void should_fail_when_name_is_null() {
        Executable exec = () -> useCase.execute(new CreateTodoCommand(todolistId, null));
        Throwable thrown = assertThrows(ConstraintViolationException.class, exec);
        assertEquals("label: must not be blank", thrown.getMessage());
        verify(writeRepository, never()).save(any());
    }

    @Test
    void should_fail_when_todolist_not_found() {
        Executable exec = () -> useCase.execute(new CreateTodoCommand(TodolistId.of("57106e00-71e6-4cee-8f56-edec23eddbef"), "task name"));
        Throwable thrown = assertThrows(TodolistNotFoundException.class, exec);
        assertEquals("Todolist 57106e00-71e6-4cee-8f56-edec23eddbef cannot be found.", thrown.getMessage());
        verify(writeRepository, never()).save(any());
    }
}
