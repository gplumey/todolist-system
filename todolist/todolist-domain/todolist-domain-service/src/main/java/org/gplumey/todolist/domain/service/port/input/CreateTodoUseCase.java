package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.CommandHandler;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodoCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateTodoUseCase implements CommandHandler<Todo, CreateTodoCommand> {

    private final TodolistWriteRepository writeRepository;
    private final TodolistReadRepository readRepository;

    private final UsecaseValidator validator;

    @Override
    public Todo execute(CreateTodoCommand addTodoCommand) {
        validator.validate(addTodoCommand);
        TodolistId todolistId = addTodoCommand.todolistId();
        Todolist todolist = readRepository.get(todolistId).orElseThrow(() -> new TodolistNotFoundException(todolistId));
        Todo todo = todolist.addTodo(addTodoCommand.label());
        writeRepository.save(todolist);
        return todo;
    }
}
