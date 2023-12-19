package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.Response.TodoResource;
import org.gplumey.todolist.application.rest.dto.request.CreateTodoDto;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.CreateTodoUseCase;
import org.gplumey.todolist.domain.service.port.input.GetTodoUsecase;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;
import org.gplumey.todolist.domain.service.port.input.query.GetTodoQuery;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/todolist/{todolistId}/todo")
@AllArgsConstructor
public class TodoRestController {

    private final GetTodoUsecase getTodoUsecase;
    private final CreateTodoUseCase createTodoUseCase;


    @GetMapping("/{todoId}")
    public TodoResource get(@PathVariable UUID todolistId, @PathVariable UUID todoId) {
        var query = new GetTodoQuery(TodolistId.of(todolistId), TodoId.of(todoId));
        var todo = getTodoUsecase.request(query);
        return TodoResource.of(todo);
    }

    @PostMapping
    public TodoResource post(@PathVariable UUID todolistId, @RequestBody CreateTodoDto body) {
        var command = new CreateTodoCommand(TodolistId.of(todolistId), body.label());
        Todo todo = createTodoUseCase.execute(command);
        return TodoResource.of(todo);
    }
}
