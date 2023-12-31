package org.gplumey.todolist.domain.service.impl;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.eventing.DomainEventDispatcher;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.core.execption.TodolistAlreadyExistsException;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Observed
public class CreateTodolistUseCaseImpl implements UseCases.Commands.CreateTodolistUseCase {


    private final DomainEventDispatcher dispatcher;
    private final TodolistWriteRepository repository;
    private final TodolistReadRepository readRepository;

    private final UsecaseValidator validator;

    @Override
    public Todolist execute(CreateTodolistCommand command) {
        validator.validate(command);
        var todolistName = TodolistName.of(command.getName());
        if (readRepository.findByName(todolistName).isPresent()) {
            throw new TodolistAlreadyExistsException(todolistName);
        }

        Todolist newTodolist = Todolist.create(command.getName());
        newTodolist.fireEvents(dispatcher);
        return repository.save(newTodolist);
    }
}
