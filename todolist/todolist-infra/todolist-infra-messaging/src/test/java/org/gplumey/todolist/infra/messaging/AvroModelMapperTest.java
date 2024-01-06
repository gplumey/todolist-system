package org.gplumey.todolist.infra.messaging;

import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoLabel;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.core.event.TodoCreatedEvent;
import org.gplumey.todolist.domain.core.event.TodoListCreatedEvent;
import org.gplumey.todolist.infra.messaging.avro.model.TodoCreatedEventAvro;
import org.gplumey.todolist.infra.messaging.avro.model.TodolistCreatedEventAvro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class AvroModelMapperTest {

    String todolistId = "391a973c-c7d5-40b8-a45e-e0b5423d33b2";

    String todolistName = "My todolist";

    String todoId = "0df7b355-f794-437b-9ba3-5ec829a9a62d";
    String todoLabel = "my first task";

    Todo todo = Todo.builder().id(TodoId.of(todoId)).todolistId(TodolistId.of(todolistId)).label(TodoLabel.of(todoLabel)).build();
    Todolist todolist = Todolist.builder().id(TodolistId.of(todolistId)).name(TodolistName.of(todolistName)).todos(List.of(todo)).build();


    @Nested
    class TodoListCreatedEventTest {
        @Test
        void shouldAdaptAllFields() {
            var event = new TodoListCreatedEvent(todolist);
            var result = (TodolistCreatedEventAvro) AvroModelMapper.adaptor(event);

            Assertions.assertAll("assert all field are mapped",
                    () -> Assertions.assertEquals(todolistId, result.getTodolist().getId()),
                    () -> Assertions.assertEquals(todolistName, result.getTodolist().getName()),
                    () -> Assertions.assertEquals(1, result.getTodolist().getTodos().size()),
                    () -> Assertions.assertEquals(todoId, result.getTodolist().getTodos().get(0).getId()),
                    () -> Assertions.assertEquals(todoLabel, result.getTodolist().getTodos().get(0).getLabel())

            );
        }
    }

    @Nested
    class TodoCreatedEventTest {
        @Test
        void shouldAdaptAllFields() {
            var event = new TodoCreatedEvent(todolist, todo);
            var result = (TodoCreatedEventAvro) AvroModelMapper.adaptor(event);

            Assertions.assertAll("assert all field are mapped",
                    () -> Assertions.assertEquals(todoId, result.getTodo().getId()),
                    () -> Assertions.assertEquals(todoLabel, result.getTodo().getLabel())
            );
        }
    }
}
