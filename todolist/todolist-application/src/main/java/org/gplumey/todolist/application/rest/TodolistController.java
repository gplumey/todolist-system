package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.TodolistDto;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/todolist")
@AllArgsConstructor
public class TodolistController {

    private final CreateTodolistUserCase createTodolistUserCase;
    private final GetAllTodolistUseCase getAllTodolistUseCase;

    @GetMapping
    public List<TodolistDto> list() {
        Iterable<Todolist> data = getAllTodolistUseCase.request(new GetAllTodolistQuery());
        return StreamSupport.stream(data.spliterator(), true)
                            .map(TodolistDto::of)
                            .collect(Collectors.toList());
    }

    @PostMapping
    public TodolistDto create(@RequestBody CreateTodolistCommand command) {

        Todolist todolist = createTodolistUserCase.execute(command);

        return TodolistDto.of(todolist);
    }
}
