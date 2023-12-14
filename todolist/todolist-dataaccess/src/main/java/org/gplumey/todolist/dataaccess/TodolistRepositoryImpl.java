package org.gplumey.todolist.dataaccess;

import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.output.TodolistRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodolistRepositoryImpl implements TodolistRepository {
    @Override
    public Iterable<Todolist> findAll() {
        return List.of(Todolist.builder().name(TodolistName.of("first todolist")).build());
    }
}
