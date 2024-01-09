package org.gplumey.todolist.domain.service.port.usecase.create;

import jakarta.validation.ConstraintViolationException;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.query.GetTodolistQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


@SpringBootTest
class GetTodolistUseCaseTest {

    @Autowired
    UseCases.Queries.GetTodolistUsecase useCase;

    @Autowired
    TodolistReadRepository readRepository;

    String TODOLIST_UUID = "095d6c0c-69bc-48f8-b139-d3e06194cdc5";
    TodolistId todolistId = TodolistId.of(TODOLIST_UUID);
    Todolist todolist = Todolist.builder().id(todolistId).name(TodolistName.of("Test todolist")).build();


    @BeforeEach
    void setup() {
        reset(readRepository);
        LocaleContextHolder.setLocale(Locale.US);
        when(readRepository.get(todolistId)).thenReturn(Optional.of(todolist));
    }

    @Test
    void should_create_todolist_given_valid_request() {
        Todolist todolist = useCase.request(getTodolistQueryAdpator(TODOLIST_UUID));
        assertEquals("Test todolist", todolist.getName().getValue());
    }

    @Test
    void should_fail_when_id_is_null() {
        Executable exec = () -> useCase.request(getTodolistQueryAdpator(null));
        Throwable thrown = assertThrows(ConstraintViolationException.class, exec);
        assertEquals("todolistId: must not be null", thrown.getMessage());
    }

    private GetTodolistQuery getTodolistQueryAdpator(String todolistUUID) {

        return new GetTodolistQuery() {
            @Override
            public TodolistId getTodolistId() {
                return todolistUUID != null ? TodolistId.of(todolistUUID) : null;
            }
        };
    }
}
