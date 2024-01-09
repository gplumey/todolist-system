package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.Response.TodoResource;
import org.gplumey.todolist.application.rest.dto.request.CreateTodoDto;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.query.GetTodoQuery;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/todolist/{todolistId}/todo")
@AllArgsConstructor
public class TodoRestController {

    private final UseCases.Queries.GetTodoUsecase getTodoUsecase;
    private final UseCases.Commands.CreateTodoUseCase createTodoUseCase;


    @GetMapping("/{todoId}")
    public TodoResource get(@PathVariable UUID todolistId, @PathVariable UUID todoId) {
        var todo = getTodoUsecase.request(new GetTodoQuery() {
            @Override
            public TodolistId todolistId() {
                return TodolistId.of(todolistId);
            }

            @Override
            public TodoId todoId() {
                return TodoId.of(todoId);
            }
        });
        return TodoResource.of(todo);
    }

    @PostMapping
    public TodoResource post(@PathVariable UUID todolistId, @RequestBody CreateTodoDto body) {
        var command = CreateTodoDto.adaptor(todolistId, body);
        Todo todo = createTodoUseCase.execute(command);
        return TodoResource.of(todo);
    }
}
