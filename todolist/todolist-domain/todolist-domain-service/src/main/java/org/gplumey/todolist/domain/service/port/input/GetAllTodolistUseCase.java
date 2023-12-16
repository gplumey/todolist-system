package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.QueryHandler;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetAllTodolistUseCase implements QueryHandler<Iterable<Todolist>, GetAllTodolistQuery> {

    private final TodolistReadRepository repository;

    @Override
    public Iterable<Todolist> request(GetAllTodolistQuery getAllTodolistQuery) {
        return repository.findAll();
    }
}
