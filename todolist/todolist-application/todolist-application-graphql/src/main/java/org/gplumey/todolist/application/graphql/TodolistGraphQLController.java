package org.gplumey.todolist.application.graphql;

import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.gplumey.todolist.application.graphql.builder.GraphDtoBuilder;
import org.gplumey.todolist.application.graphql.schema.generated.AddTaskInput;
import org.gplumey.todolist.application.graphql.schema.generated.TaskGQL;
import org.gplumey.todolist.application.graphql.schema.generated.TodolistGQL;
import org.gplumey.todolist.domain.core.entity.Task;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.AddTaskUseCase;
import org.gplumey.todolist.domain.service.port.input.GetAllTodolistUseCase;
import org.gplumey.todolist.domain.service.port.input.command.AddTaskCommand;
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
    private final AddTaskUseCase addTaskUseCase;

    @QueryMapping
    public List<TodolistGQL> todolists(DataFetchingFieldSelectionSet selectionSet) {
        return getAllTodolistUseCase.request(new GetAllTodolistQuery()).stream().map(todolist -> GraphDtoBuilder.of(todolist, selectionSet)).toList();
    }

    @MutationMapping
    public TaskGQL addTask(@Argument AddTaskInput addTaskInput, DataFetchingFieldSelectionSet selectionSet) {
        Task task = addTaskUseCase.execute(new AddTaskCommand(TodolistId.of(addTaskInput.getTodolistId()), addTaskInput.getLabel()));
        return GraphDtoBuilder.of(task, selectionSet);
    }
}
