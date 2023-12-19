package org.gplumey.todolist.application.rest;

import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.input.command.CreateTodolistCommand;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodolistRestControllerTest {

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
    void should_get_all() {
        String defaultTodoListName = "Default todolist";
        Collection<Todolist> mockData = List.of(Todolist.builder().id(TodolistId.create()).name(TodolistName.of(defaultTodoListName)).build());
        when(readRepository.findAll()).thenReturn(mockData);
        get(HttpStatus.OK)
                .jsonPath("$.length()")
                .isEqualTo(1)
                .jsonPath("$[0].name")
                .isEqualTo(defaultTodoListName);
    }


    private WebTestClient.BodyContentSpec postCreateTodolistCommand(CreateTodolistCommand command, HttpStatus expectedStatus) {
        return webTestClient.post()
                            .uri("/todolist")
                            .body(Mono.just(command), CreateTodolistCommand.class)
                            .exchange()
                            .expectStatus()
                            .isEqualTo(expectedStatus.value())
                            .expectBody();
    }

    @Test
    void should_return_problemDetail_on_IllegalStateException() {
        CreateTodolistCommand command = new CreateTodolistCommand(null);
        postCreateTodolistCommand(command, HttpStatus.BAD_REQUEST).jsonPath("$.detail").isEqualTo("name: must not be blank");
    }

    @Test
    void should_return_create_todolist() {
        CreateTodolistCommand command = new CreateTodolistCommand("My todolist");
        postCreateTodolistCommand(command, HttpStatus.OK).jsonPath("$.name").isEqualTo("My todolist").jsonPath("$.id").isNotEmpty();
    }


    private WebTestClient.BodyContentSpec get(TodolistId todolistId, HttpStatus expectedStatus) {
        return webTestClient.get().uri("/todolist/" + todolistId.getValue()).exchange().expectStatus().isEqualTo(expectedStatus.value()).expectBody();
    }

    private WebTestClient.BodyContentSpec get(HttpStatus expectedStatus) {
        return webTestClient.get().uri("/todolist").exchange().expectStatus().isEqualTo(expectedStatus.value()).expectBody();
    }

    @Test
    void should_get_one_by_id() {
        Todolist todolist = Todolist.builder().id(TodolistId.create()).name(TodolistName.of("test todolist")).build();
        when(readRepository.get(any())).thenReturn(Optional.of(todolist));
        get(todolist.getId(), HttpStatus.OK).jsonPath("$.name").isEqualTo("test todolist");
    }
}
