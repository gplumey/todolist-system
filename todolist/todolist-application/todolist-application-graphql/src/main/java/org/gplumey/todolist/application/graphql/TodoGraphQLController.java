package org.gplumey.todolist.application.graphql;

import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.gplumey.todolist.application.graphql.builder.GraphQLBuilder;
import org.gplumey.todolist.application.graphql.schema.generated.CreateTodoInputGraphQL;
import org.gplumey.todolist.application.graphql.schema.generated.TodoGraphQL;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.CreateTodoUseCase;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TodoGraphQLController {


    private final CreateTodoUseCase createTodoUseCase;


    @MutationMapping
    public TodoGraphQL createTodo(@Argument CreateTodoInputGraphQL addTaskInput, DataFetchingFieldSelectionSet selectionSet) {
        Todo todo = createTodoUseCase.execute(new CreateTodoCommand(TodolistId.of(addTaskInput.getTodolistId()), addTaskInput.getLabel()));
        return GraphQLBuilder.of(todo, selectionSet);
    }
}
