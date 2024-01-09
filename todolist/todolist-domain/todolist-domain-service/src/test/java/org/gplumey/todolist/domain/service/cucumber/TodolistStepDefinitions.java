package org.gplumey.todolist.domain.service.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.validation.ConstraintViolationException;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.core.execption.TodolistAlreadyExistsException;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

public class TodolistStepDefinitions {
    List<Throwable> exceptions = new ArrayList<>();
    boolean exceptionThrow = false;

    @Autowired
    UseCases.Commands.CreateTodolistUseCase createTodolistUseCase;

    @Autowired
    TodolistReadRepository todolistReadRepository;


    @BeforeAll
    public static void beforeAll() {
        LocaleContextHolder.setLocale(Locale.US);
    }

    @Given("Only default todolist exists")
    public void only_default_todolist_exists() {

    }

    @Given("already created todolists")
    public void existing_todolists(DataTable dataTable) {
        Mockito.when(todolistReadRepository.findByName(any())).thenAnswer(invocationOnMock -> {
            TodolistName name = invocationOnMock.getArgument(0);
            if (dataTable.asList().contains(name.getValue())) {
                return Optional.of(Todolist.builder().id(TodolistId.create()).name(name).build());
            } else {
                return Optional.empty();
            }
        });
    }


    @When("create {string}")
    public void create(String todolistName) {
        var command = new CreateTodolistCommand() {
            @Override
            public String getName() {
                return todolistName;
            }
        };
        try {
            createTodolistUseCase.execute(command);
        } catch (Throwable throwable) {
            exceptionThrow = true;
            exceptions.add(throwable);
        }
    }

    @Then("todolist get created")
    public void todolist_get_created() {
        assertThat(exceptionThrow, is(false));
    }

    @Then("todolist creation failed")
    public void todolist_creation_failed() {
        assertThat(exceptions, is(not(empty())));
    }

    @Then("thrown {string} with message {string}")
    public void thrown_with_message(String exceptionType, String message) {
        var expectedExceptionClass = switch (exceptionType) {
            case "Constraint Violation" -> ConstraintViolationException.class;
            case "Todolist Already Exists" -> TodolistAlreadyExistsException.class;
            default -> throw new IllegalStateException("Unexpected value: " + exceptionType);
        };
        Throwable throwable = exceptions.get(0);
        assertThat(throwable, is(instanceOf(expectedExceptionClass)));
        assertThat(throwable.getMessage(), is(message));
    }
}
