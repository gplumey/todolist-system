package org.gplumey.todolist.domain.service.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.springframework.beans.factory.annotation.Autowired;

public class StepDefinitions {


    @Autowired
    UseCases.Commands.CreateTodolistUseCase createTodolistUseCase;

    @Given("Only default todolist exists")
    public void only_default_todolist_exists() {

    }

    @When("create {string}")
    public void create(String todolistName) {
        var command = new CreateTodolistCommand() {
            @Override
            public String getName() {
                return todolistName;
            }
        };

        createTodolistUseCase.execute(command);
    }

    @Then("todolist get created")
    public void todolist_get_created() {

    }
}
