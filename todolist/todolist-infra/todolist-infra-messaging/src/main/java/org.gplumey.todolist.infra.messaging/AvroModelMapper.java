package org.gplumey.todolist.infra.messaging;

import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.event.TodoCreatedEvent;
import org.gplumey.todolist.domain.core.event.TodoListCreatedEvent;
import org.gplumey.todolist.domain.core.event.TodolistDeletedEvent;
import org.gplumey.todolist.infra.messaging.avro.model.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class AvroModelMapper {

    public static Object adaptor(DomainEvent event) {
        return switch (event) {
            case TodoListCreatedEvent evt -> adaptor(evt);
            case TodoCreatedEvent evt -> adaptor(evt);
            case TodolistDeletedEvent evt -> adaptor(evt);
            default -> throw new IllegalArgumentException(event.getClass() + " not supported");
        };
    }

    private static TodolistCreatedEventAvro adaptor(TodoListCreatedEvent event) {
        return TodolistCreatedEventAvro.newBuilder()
                                       .setTodolist(TodolistAvro.newBuilder()
                                                                .setId(event.getSource().getId().getValue().toString())
                                                                .setName(event.getSource().getName().value())
                                                                .setTodos(map(event.getSource().getTodos()))
                                                                .build()).setHeader(mapHeader(event)).build();
    }

    private static TodoCreatedEventAvro adaptor(TodoCreatedEvent event) {
        var todo = TodoAvro.newBuilder()
                           .setId(event.getTodo().getId().getValue().toString())
                           .setLabel(event.getTodo().getLabel().value())
                           .setTodolistId(event.getSource().getId().getValue().toString())
                           .build();
        return TodoCreatedEventAvro.newBuilder()
                                   .setTodo(todo).setHeader(mapHeader(event)).build();
    }

    private static TodolistDeletedEventAvro adaptor(TodolistDeletedEvent event) {
        return TodolistDeletedEventAvro.newBuilder()
                                       .setTodolist(TodolistAvro.newBuilder()
                                                                .setId(event.getSource().getId().getValue().toString())
                                                                .setName(event.getSource().getName().value())
                                                                .setTodos(map(event.getSource().getTodos()))
                                                                .build()).setHeader(mapHeader(event)).build();
    }


    private static List<TodoAvro> map(Collection<Todo> todos) {
        return todos.stream().map(AvroModelMapper::map).toList();
    }

    private static EventHeaderAvro mapHeader(DomainEvent event) {
        return EventHeaderAvro.newBuilder()
                              .setEventName(event.getClass().getSimpleName())
                              .setEventId(event.getId())
                              .setCreatedAt(event.getCreatedAt())
                              .setSagaId(UUID.randomUUID())
                              .build();
    }

    private static TodoAvro map(Todo todo) {
        return TodoAvro.newBuilder()
                       .setId(todo.getId().getValue().toString())
                       .setTodolistId(todo.getTodolistId().getValue().toString())
                       .setLabel(todo.getLabel().value())
                       .build();
    }
}
