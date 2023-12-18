package org.gplumey.todolist.application.graphql;

import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.gplumey.todolist.application.graphql.builder.GraphDtoBuilder;
import org.gplumey.todolist.application.graphql.schema.generated.AddTodoInputGraphQL;
import org.gplumey.todolist.application.graphql.schema.generated.TodoGraphQL;
import org.gplumey.todolist.application.graphql.schema.generated.TodolistGraphQL;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.AddTodoUseCase;
import org.gplumey.todolist.domain.service.port.input.GetAllTodolistUseCase;
import org.gplumey.todolist.domain.service.port.input.command.AddTodoCommand;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodolistGraphQLController {

    private final GetAllTodolistUseCase getAllTodolistUseCase;
    private final AddTodoUseCase addTodoUseCase;

    @QueryMapping
    public List<TodolistGraphQL> todolists(DataFetchingFieldSelectionSet selectionSet) {
        return getAllTodolistUseCase.request(new GetAllTodolistQuery()).stream().map(todolist -> GraphDtoBuilder.of(todolist, selectionSet)).toList();
    }

    @MutationMapping
    public TodoGraphQL addTodo(@Argument AddTodoInputGraphQL addTaskInput, DataFetchingFieldSelectionSet selectionSet) {
        Todo todo = addTodoUseCase.execute(new AddTodoCommand(TodolistId.of(addTaskInput.getTodolistId()), addTaskInput.getLabel()));
        return GraphDtoBuilder.of(todo, selectionSet);
    }
}
