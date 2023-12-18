package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.AddTodoDto;
import org.gplumey.todolist.application.rest.dto.TodoResource;
import org.gplumey.todolist.application.rest.dto.TodolistResource;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.*;
import org.gplumey.todolist.domain.service.port.input.command.AddTodoCommand;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.input.query.GetTodoQuery;
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
    private final GetTodoUsecase getTodoUsecase;
    private final AddTodoUseCase addTodoUseCase;

    @GetMapping
    public List<TodolistResource> list() {
        Iterable<Todolist> data = getAllTodolistUseCase.request(new GetAllTodolistQuery());
        return StreamSupport.stream(data.spliterator(), true)
                            .map(TodolistResource::of)
                            .toList();
    }

    @GetMapping("/{id}")
    public TodolistResource get(@PathVariable UUID id) {
        return TodolistResource.of(getTodolistUsecase.request(new GetTodolistQuery(TodolistId.of(id))));
    }

    @GetMapping("/{todolistId}/todo/{todoId}")
    public TodoResource getTodoByTodolistIdAndTodoId(@PathVariable UUID todolistId, @PathVariable UUID todoId) {
        return TodoResource.of(getTodoUsecase.request(new GetTodoQuery(TodolistId.of(todolistId), TodoId.of(todoId))));
    }

    @PostMapping
    public TodolistResource create(@RequestBody CreateTodolistCommand command) {
        Todolist todolist = createTodolistUserCase.execute(command);
        return TodolistResource.of(todolist);
    }

    @PostMapping("/{todolistId}/todo")
    public TodoResource postTodo(@PathVariable UUID todolistId, @RequestBody AddTodoDto body) {
        AddTodoCommand command = new AddTodoCommand(TodolistId.of(todolistId), body.label());
        Todo todo = addTodoUseCase.execute(command);
        return TodoResource.of(todo);
    }
}
