package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.QueryHandler;
import org.gplumey.todolist.domain.core.entity.Task;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.execption.TaskNotFoundException;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.query.GetTaskQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetTaskUsecase implements QueryHandler<Task, GetTaskQuery> {

    private final TodolistReadRepository repository;
    private final UsecaseValidator validator;

    @Override
    public Task request(GetTaskQuery query) {
        validator.validate(query);
        TodolistId todolistId = query.todolistId();
        Todolist todolist = repository.get(todolistId).orElseThrow(() -> new TodolistNotFoundException(query.todolistId()));
        Task task = todolist.getTasks()
                            .stream()
                            .filter(t -> t.getId().equals(query.taskId()))
                            .findFirst()
                            .orElseThrow(() -> new TaskNotFoundException(query.taskId()));
        return task;
    }
}
