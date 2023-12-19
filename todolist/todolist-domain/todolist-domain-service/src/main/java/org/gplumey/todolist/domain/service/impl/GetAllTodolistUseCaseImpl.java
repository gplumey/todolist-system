package org.gplumey.todolist.domain.service.impl;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@AllArgsConstructor
@Component
@Observed
public class GetAllTodolistUseCaseImpl implements UseCases.Queries.GetAllTodolistUseCase {

    private final TodolistReadRepository repository;

    @Override
    public Collection<Todolist> request(GetAllTodolistQuery getAllTodolistQuery) {
        return repository.findAll();
    }
}
