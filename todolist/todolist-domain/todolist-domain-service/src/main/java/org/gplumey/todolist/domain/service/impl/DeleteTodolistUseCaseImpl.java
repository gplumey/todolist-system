package org.gplumey.todolist.domain.service.impl;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.eventing.DomainEventDispatcher;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.command.DeleteTodolistCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
@Observed
public class DeleteTodolistUseCaseImpl implements UseCases.Commands.DeleteTodolistUseCase {


    private final DomainEventDispatcher dispatcher;
    private final TodolistWriteRepository writeRepository;
    private final TodolistReadRepository readRepository;

    private final UsecaseValidator validator;

    @Override
    public Void execute(DeleteTodolistCommand command) {
        //validator.validate(command);

        Optional<Todolist> optional = readRepository.findById(command.getTodolistId());

        Todolist todolist = optional.orElseThrow(() -> {
            throw new TodolistNotFoundException(command.getTodolistId());
        });


        todolist.delete();
        todolist.fireEvents(dispatcher);
        writeRepository.delete(todolist);
        return null;
    }
}
