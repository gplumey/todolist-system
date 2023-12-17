package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.AddTaskDto;
import org.gplumey.todolist.application.rest.dto.TaskDto;
import org.gplumey.todolist.application.rest.dto.TodolistDto;
import org.gplumey.todolist.domain.core.entity.Task;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TaskId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.*;
import org.gplumey.todolist.domain.service.port.input.command.AddTaskCommand;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.input.query.GetTaskQuery;
import org.gplumey.todolist.domain.service.port.input.query.GetTodolistQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/todolist")
@AllArgsConstructor
public class TodolistRestController {
    private final CreateTodolistUseCase createTodolistUserCase;
    private final GetAllTodolistUseCase getAllTodolistUseCase;
    private final GetTodolistUsecase getTodolistUsecase;
    private final GetTaskUsecase getTaskUsecase;
    private final AddTaskUseCase addTaskUseCase;

    @GetMapping
    public List<TodolistDto> list() {
        Iterable<Todolist> data = getAllTodolistUseCase.request(new GetAllTodolistQuery());
        return StreamSupport.stream(data.spliterator(), true)
                            .map(TodolistDto::of)
                            .toList();
    }

    @GetMapping("/{id}")
    public TodolistDto get(@PathVariable UUID id) {
        return TodolistDto.of(getTodolistUsecase.request(new GetTodolistQuery(TodolistId.of(id))));
    }

    @GetMapping("/{todolistId}/task/{taskId}")
    public TaskDto getTask(@PathVariable UUID todolistId, @PathVariable UUID taskId) {
        return TaskDto.of(getTaskUsecase.request(new GetTaskQuery(TodolistId.of(todolistId), TaskId.of(taskId))));
    }

    @PostMapping
    public TodolistDto create(@RequestBody CreateTodolistCommand command) {
        Todolist todolist = createTodolistUserCase.execute(command);
        return TodolistDto.of(todolist);
    }

    @PostMapping("/{todolistId}/task")
    public TaskDto postTask(@PathVariable UUID todolistId, @RequestBody AddTaskDto body) {
        AddTaskCommand command = new AddTaskCommand(TodolistId.of(todolistId), body.label());
        Task task = addTaskUseCase.execute(command);
        return TaskDto.of(task);
    }
}
