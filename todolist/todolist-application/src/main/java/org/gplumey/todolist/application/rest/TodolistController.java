package org.gplumey.todolist.application.rest;


import lombok.AllArgsConstructor;
import org.gplumey.todolist.application.rest.dto.TodolistDto;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.TodolistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/todolist")
@AllArgsConstructor
public class TodolistController {


    private final TodolistService todolistService;
    @GetMapping
    public List<TodolistDto> list() {
        Iterable<Todolist> data = todolistService.readAll();
        return StreamSupport.stream(data.spliterator(), true)
                            .map(TodolistDto::of)
                            .collect(Collectors.toList());
    }

}
