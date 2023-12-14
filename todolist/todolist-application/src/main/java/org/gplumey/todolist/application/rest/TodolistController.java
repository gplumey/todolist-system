package org.gplumey.todolist.application.rest;


import org.gplumey.todolist.application.rest.dto.TodolistDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodolistController {

    @GetMapping
    public List<TodolistDto> list() {
        return List.of(new TodolistDto("Default todolist"));
    }

}
