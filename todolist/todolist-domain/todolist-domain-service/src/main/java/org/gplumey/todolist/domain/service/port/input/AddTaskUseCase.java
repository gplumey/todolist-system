package org.gplumey.todolist.domain.service.port.input;

import lombok.AllArgsConstructor;
import org.gplumey.common.domain.core.usecase.CommandHandler;
import org.gplumey.todolist.domain.core.entity.Task;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.gplumey.todolist.domain.service.port.input.command.AddTaskCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.gplumey.todolist.domain.service.validation.UsecaseValidator;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddTaskUseCase implements CommandHandler<Task, AddTaskCommand> {

    private final TodolistWriteRepository writeRepository;
    private final TodolistReadRepository readRepository;

    private final UsecaseValidator validator;

    @Override
    public Task execute(AddTaskCommand addTaskCommand) {
        validator.validate(addTaskCommand);
        TodolistId todolistId = addTaskCommand.todolistId();
        Todolist todolist = readRepository.get(todolistId).orElseThrow(() -> new TodolistNotFoundException(todolistId));
        Task task = todolist.addTask(addTaskCommand.taskname());
        writeRepository.save(todolist);
        return task;
    }
}
