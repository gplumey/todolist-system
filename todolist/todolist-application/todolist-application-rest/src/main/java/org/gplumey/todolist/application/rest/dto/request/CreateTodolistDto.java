package org.gplumey.todolist.application.rest.dto.request;

import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;

public record CreateTodolistDto(String name) {

    public static CreateTodolistCommand adaptor(CreateTodolistDto dto) {
        return new CreateTodolistCommand() {
            @Override
            public String getName() {
                return dto.name();
            }
        };
    }
}
