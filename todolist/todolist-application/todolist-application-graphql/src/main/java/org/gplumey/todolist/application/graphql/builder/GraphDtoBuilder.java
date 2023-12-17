package org.gplumey.todolist.application.graphql.builder;

import graphql.schema.DataFetchingFieldSelectionSet;
import org.gplumey.todolist.application.graphql.schema.generated.TaskGQL;
import org.gplumey.todolist.application.graphql.schema.generated.TodolistGQL;
import org.gplumey.todolist.domain.core.entity.Task;
import org.gplumey.todolist.domain.core.entity.Todolist;

public class GraphDtoBuilder {

    public static TodolistGQL of(Todolist entity, DataFetchingFieldSelectionSet selectionSet) {
        var dto = new TodolistGQL();
        dto.setId(entity.getId().getValue().toString());
        dto.setName(entity.getName().getValue());

        if (selectionSet.contains("tasks")) {
            dto.setTasks(entity.getTasks().stream().map(task -> of(task, selectionSet)).toList());
        }
        return dto;
    }

    public static TaskGQL of(Task entity, DataFetchingFieldSelectionSet selectionSet) {
        var dto = new TaskGQL();
        dto.setId(entity.getId().getValue().toString());
        dto.setLabel(entity.getLabel().getValue());
        return dto;
    }
}
