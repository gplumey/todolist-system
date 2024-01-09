package org.gplumey.todolist.domain.service.impl;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.eventing.DomainEventDispatcher;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.UseCases;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Observed
public class CreateTodoUseCaseImpl implements UseCases.Commands.CreateTodoUseCase {
    private final DomainEventDispatcher dispatcher;
    private final TodolistWriteRepository writeRepository;
    private final TodolistReadRepository readRepository;

    private final UsecaseValidator validator;

    @Override
    public Todo execute(CreateTodoCommand addTodoCommand) {
        validator.validate(addTodoCommand);
        TodolistId todolistId = addTodoCommand.getTodolistId();
        Todolist todolist = readRepository.findById(todolistId).orElseThrow(() -> new TodolistNotFoundException(todolistId));
        Todo todo = todolist.addTodo(addTodoCommand.getLabel());
        writeRepository.save(todolist);
        todolist.fireEvents(dispatcher);
        return todo;
    }
}
