package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.QueryHandler;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@AllArgsConstructor
@Component
public class GetAllTodolistUseCase implements QueryHandler<Collection<Todolist>, GetAllTodolistQuery> {

    private final TodolistReadRepository repository;

    @Override
    public Collection<Todolist> request(GetAllTodolistQuery getAllTodolistQuery) {
        return repository.findAll();
    }
}
