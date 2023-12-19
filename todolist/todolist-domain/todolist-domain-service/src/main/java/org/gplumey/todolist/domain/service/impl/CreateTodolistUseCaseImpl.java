package org.gplumey.todolist.domain.service.impl;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Observed
public class CreateTodolistUseCaseImpl implements UseCases.Commands.CreateTodolistUseCase {

    private final TodolistWriteRepository repository;

    private final UsecaseValidator validator;

    @Override
    public Todolist execute(CreateTodolistCommand command) {
        validator.validate(command);
        Todolist newTodolist = Todolist.create(command.getName());
        return repository.save(newTodolist);
    }
}
