package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.CommandHandler;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CreateTodolistUseCase implements CommandHandler<Todolist, CreateTodolistCommand> {

    private final TodolistWriteRepository repository;

    private final UsecaseValidator validator;

    @Override
    public Todolist execute(CreateTodolistCommand command) {
        validator.validate(command);
        Todolist newTodolist = Todolist.create(command.name());
        return repository.save(newTodolist);
    }
}
