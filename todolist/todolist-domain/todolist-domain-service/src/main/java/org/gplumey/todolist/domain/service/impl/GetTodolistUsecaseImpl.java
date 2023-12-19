package org.gplumey.todolist.domain.service.impl;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.query.GetTodolistQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Observed
public class GetTodolistUsecaseImpl implements UseCases.Queries.GetTodolistUsecase {

    private final TodolistReadRepository repository;
    private final UsecaseValidator validator;


    @Override
    public Todolist request(GetTodolistQuery query) {
        validator.validate(query);
        TodolistId todolistId = query.getTodolistId();
        return repository.get(todolistId).orElseThrow(() -> new TodolistNotFoundException(query.getTodolistId()));
    }
}
