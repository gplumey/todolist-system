package org.gplumey.todolist.application.graphql;

import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.gplumey.todolist.application.graphql.builder.GraphQLBuilder;
import org.gplumey.todolist.application.graphql.schema.generated.CreateTodoInputGraphQL;
import org.gplumey.todolist.application.graphql.schema.generated.TodoGraphQL;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TodoGraphQLController {


    private final UseCases.Commands.CreateTodoUseCase createTodoUseCase;


    @MutationMapping
    public TodoGraphQL createTodo(@Argument CreateTodoInputGraphQL createTodoInput, DataFetchingFieldSelectionSet selectionSet) {
        Todo todo = createTodoUseCase.execute(GraphQLAdapter.adaptor(createTodoInput));
        return GraphQLBuilder.of(todo, selectionSet);
    }
}
