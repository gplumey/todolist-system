package org.gplumey.todolist.infra.messaging;

import org.gplumey.todolist.domain.core.event.TodolistEvent;
import org.gplumey.todolist.infra.messaging.avro.model.TodolistAvro;
import org.gplumey.todolist.infra.messaging.avro.model.TodolistAvroModel;

import java.time.Instant;
import java.util.UUID;

public class AvroModelMapper {

    public static TodolistAvroModel adaptor(TodolistEvent event) {
        return TodolistAvroModel.newBuilder()
                                .setTodolist(TodolistAvro.newBuilder()
                                                         .setId(event.getSource().getId().getValue())
                                                         .setName(event.getSource().getName().getValue())
                                                         .build())
                                .setCreatedAt(Instant.now())
                                .setSagaId(UUID.randomUUID())
                                .build();
    }
}
