package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.QueryHandler;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.query.GetTodolistQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetTodolistUsecase implements QueryHandler<Todolist, GetTodolistQuery> {

    private final TodolistReadRepository repository;
    private final UsecaseValidator validator;

    @Override
    public Todolist request(GetTodolistQuery query) {
        validator.validate(query);
        TodolistId todolistId = query.todolistId();
        return repository.get(todolistId).orElseThrow(() -> new TodolistNotFoundException(query.todolistId()));
    }
}
