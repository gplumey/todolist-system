package org.gplumey.todolist.application.graphql;

import org.gplumey.todolist.application.graphql.schema.generated.CreateTodoInputGraphQL;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;

public class GraphQLAdapter {

    static CreateTodoCommand adaptor(CreateTodoInputGraphQL createTodoInput) {
        return new CreateTodoCommand() {
            @Override
            public TodolistId getTodolistId() {
                return createTodoInput.getTodolistId() != null ? TodolistId.of(createTodoInput.getTodolistId()) : null;
            }

            @Override
            public String getLabel() {
                return createTodoInput.getLabel();
            }
        };
    }
}
