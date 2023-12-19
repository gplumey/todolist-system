package org.gplumey.todolist.application.rest;

import org.gplumey.todolist.application.rest.dto.request.CreateTodoDto;
import org.gplumey.todolist.domain.core.entity.Todo;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoRestControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TodolistWriteRepository writeRepository;
    @Autowired
    private TodolistReadRepository readRepository;

    @BeforeEach
    void setup() {
        reset(readRepository, writeRepository);
        when(writeRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }


    @Test
    void should_get_todo_by_todolistId_and_todoId() {
        Todolist todolist = Todolist.builder().id(TodolistId.create()).name(TodolistName.of("test todolist")).build();
        todolist.addTodo("todo 1");
        Todo todo2 = todolist.addTodo("todo 2");
        when(readRepository.get(any())).thenReturn(Optional.of(todolist));
        webTestClient.get()
                     .uri("/todolist/" + todolist.getId().getValue() + "/todo/" + todo2.getId().getValue())
                     .exchange()
                     .expectStatus()
                     .isEqualTo(HttpStatus.OK.value())
                     .expectBody()
                     .jsonPath("$.label")
                     .isEqualTo("todo 2");
    }


    @Test
    void should_create_todo() {
        Todolist todolist = Todolist.builder().id(TodolistId.create()).name(TodolistName.of("test todolist")).build();
        var newTodo = new CreateTodoDto("my new todo");
        when(readRepository.get(any())).thenReturn(Optional.of(todolist));
        webTestClient.post()
                     .uri("/todolist/" + todolist.getId().getValue() + "/todo")
                     .bodyValue(newTodo)
                     .exchange()
                     .expectStatus()
                     .isEqualTo(HttpStatus.OK.value())
                     .expectBody()
                     .jsonPath("$.label")
                     .isEqualTo("my new todo")
                     .jsonPath("$.id")
                     .isNotEmpty();
    }
}
