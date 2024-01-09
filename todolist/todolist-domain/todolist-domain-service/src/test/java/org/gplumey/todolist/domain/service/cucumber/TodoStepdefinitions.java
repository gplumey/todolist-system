package org.gplumey.todolist.domain.service.cucumber;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class TodoStepdefinitions {

    List<Throwable> exceptions = new ArrayList<>();
    boolean exceptionThrow = false;
    @Autowired
    UseCases.Commands.CreateTodoUseCase createTodoUseCase;

    @Autowired
    TodolistReadRepository todolistReadRepository;

    @Autowired
    TodolistWriteRepository todolistWriteRepository;

    TodolistId defaultTodolistId = TodolistId.create();
    Todolist defaultTodolist = Todolist.builder().id(defaultTodolistId).name(TodolistName.of("Default todolist")).build();

    @BeforeAll
    public static void beforeAll() {
        LocaleContextHolder.setLocale(Locale.US);
    }

    @When("create todo {string} in default todolist")
    public void create_todo_in_default_todolist(String label) {

        Mockito.when(todolistReadRepository.findById(defaultTodolistId)).thenReturn(Optional.of(defaultTodolist));
        var command = new CreateTodoCommand() {
            @Override
            public String getLabel() {
                return label;
            }

            @Override
            public TodolistId getTodolistId() {
                return defaultTodolistId;
            }
        };
        try {
            createTodoUseCase.execute(command);
        } catch (Throwable throwable) {
            exceptionThrow = true;
            exceptions.add(throwable);
        }
    }

    @Then("todo get created")
    public void todo_get_created() {
        ArgumentCaptor<Todolist> argument = ArgumentCaptor.forClass(Todolist.class);
        Mockito.verify(todolistWriteRepository, Mockito.times(1)).save(argument.capture());
        Assertions.assertEquals(1, argument.getValue().getTodos().size());
    }
}
