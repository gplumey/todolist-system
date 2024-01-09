package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.Response.TodolistResource;
import org.gplumey.todolist.application.rest.dto.request.CreateTodolistDto;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.input.query.GetTodolistQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/todolist")
@AllArgsConstructor
public class TodolistRestController {
    private final UseCases.Commands.CreateTodolistUseCase createTodolistUserCase;
    private final UseCases.Queries.GetAllTodolistUseCase getAllTodolistUseCase;
    private final UseCases.Queries.GetTodolistUsecase getTodolistUsecase;

    @GetMapping
    public List<TodolistResource> getAll() {
        Iterable<Todolist> data = getAllTodolistUseCase.request(new GetAllTodolistQuery() {
        });
        return StreamSupport.stream(data.spliterator(), true)
                            .map(TodolistResource::of)
                            .toList();
    }

    @GetMapping("/{id}")
    public TodolistResource getById(@PathVariable UUID id) {
        GetTodolistQuery query = () -> TodolistId.of(id);
        Todolist todolist = getTodolistUsecase.request(query);
        return TodolistResource.of(todolist);
    }


    @PostMapping
    public TodolistResource createTodolist(@RequestBody CreateTodolistDto command) {
        Todolist todolist = createTodolistUserCase.execute(CreateTodolistDto.adaptor(command));
        return TodolistResource.of(todolist);
    }
}
