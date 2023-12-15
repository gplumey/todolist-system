package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.CommandHandler;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.output.TodolistRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CreateTodolistUserCase implements CommandHandler<Todolist, CreateTodolistCommand> {

    private final TodolistRepository repository;

    private final UsecaseValidator validator;

    @Override
    public Todolist execute(CreateTodolistCommand command) {
        validator.validate(command);
        Todolist newTodolist = Todolist.create(command.name());
        Todolist savedTodolist = repository.save(newTodolist);
        return savedTodolist;
    }
}
