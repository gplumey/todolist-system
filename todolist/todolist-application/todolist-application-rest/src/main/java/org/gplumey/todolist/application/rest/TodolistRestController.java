package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.Response.TodolistResource;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.service.port.input.CreateTodolistUseCase;
import org.gplumey.todolist.domain.service.port.input.GetAllTodolistUseCase;
import org.gplumey.todolist.domain.service.port.input.GetTodolistUsecase;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
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
    private final CreateTodolistUseCase createTodolistUserCase;
    private final GetAllTodolistUseCase getAllTodolistUseCase;
    private final GetTodolistUsecase getTodolistUsecase;

    @GetMapping
    public List<TodolistResource> list() {
        Iterable<Todolist> data = getAllTodolistUseCase.request(new GetAllTodolistQuery());
        return StreamSupport.stream(data.spliterator(), true)
                            .map(TodolistResource::of)
                            .toList();
    }

    @GetMapping("/{id}")
    public TodolistResource get(@PathVariable UUID id) {
        GetTodolistQuery query = new GetTodolistQuery(TodolistId.of(id));
        Todolist todolist = getTodolistUsecase.request(query);
        return TodolistResource.of(todolist);
    }


    @PostMapping
    public TodolistResource post(@RequestBody CreateTodolistCommand command) {
        Todolist todolist = createTodolistUserCase.execute(command);
        return TodolistResource.of(todolist);
    }
}
