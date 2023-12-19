package org.gplumey.todolist.application.graphql.builder;

import graphql.schema.DataFetchingFieldSelectionSet;
import org.gplumey.todolist.application.graphql.schema.generated.TodoGraphQL;
import org.gplumey.todolist.application.graphql.schema.generated.TodolistGraphQL;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;

public class GraphQLBuilder {

    public static TodolistGraphQL of(Todolist entity, DataFetchingFieldSelectionSet selectionSet) {
        var dto = new TodolistGraphQL();
        dto.setId(entity.getId().getValue().toString());
        dto.setName(entity.getName().getValue());

        if (selectionSet.contains("todos")) {
            dto.setTodos(entity.getTodos().stream().map(todo -> of(todo, selectionSet)).toList());
        }
        return dto;
    }

    public static TodoGraphQL of(Todo entity, DataFetchingFieldSelectionSet selectionSet) {
        var dto = new TodoGraphQL();
        dto.setId(entity.getId().getValue().toString());
        dto.setLabel(entity.getLabel().getValue());
        return dto;
    }
}
