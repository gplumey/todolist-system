package org.gplumey.todolist.domain.service.impl;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.execption.TodoNotFoundException;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.query.GetTodoQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Observed
public class GetTodoUsecaseImpl implements UseCases.Queries.GetTodoUsecase {

    private final TodolistReadRepository repository;
    private final UsecaseValidator validator;

    @Override
    public Todo request(GetTodoQuery query) {
        validator.validate(query);
        TodolistId todolistId = query.todolistId();
        Todolist todolist = repository.get(todolistId).orElseThrow(() -> new TodolistNotFoundException(query.todolistId()));
        return todolist.getTodos()
                       .stream()
                       .filter(t -> t.getId().equals(query.todoId()))
                       .findFirst()
                       .orElseThrow(() -> new TodoNotFoundException(query.todoId()));
    }
}
