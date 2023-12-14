package org.gplumey.todolist.domain.service;

import lombok.AllArgsConstructor;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.TodolistService;
import org.gplumey.todolist.domain.service.port.output.TodolistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TodolistServiceImpl  implements TodolistService {

    private final TodolistRepository todolistRepository;

    @Override
    public Iterable<Todolist> readAll() {
        return todolistRepository.findAll();
    }
}
