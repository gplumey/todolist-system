package org.gplumey.todolist.application.graphql;

import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.gplumey.todolist.application.graphql.builder.GraphQLBuilder;
import org.gplumey.todolist.application.graphql.schema.generated.TodolistGraphQL;
import org.gplumey.todolist.domain.service.port.input.CreateTodoUseCase;
import org.gplumey.todolist.domain.service.port.input.GetAllTodolistUseCase;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodolistGraphQLController {

    private final GetAllTodolistUseCase getAllTodolistUseCase;
    private final CreateTodoUseCase createTodoUseCase;

    @QueryMapping
    public List<TodolistGraphQL> todolists(DataFetchingFieldSelectionSet selectionSet) {
        return getAllTodolistUseCase.request(new GetAllTodolistQuery()).stream().map(todolist -> GraphQLBuilder.of(todolist, selectionSet)).toList();
    }
}
