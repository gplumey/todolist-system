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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodolistControllerTest {

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
    void list() throws Exception {
        String defaultTodoListName = "Default todolist";
        Iterable<Todolist> mockData = List.of(Todolist.builder().id(TodolistId.create()).name(TodolistName.of(defaultTodoListName)).build());
        when(readRepository.findAll()).thenReturn(mockData);
        webTestClient.get().uri("/todolist").exchange().expectStatus().isOk()
                     .expectBody().jsonPath("$.length()").isEqualTo(1)
                     .jsonPath("$[0].name").isEqualTo(defaultTodoListName);
    }

    @Test
    void should_return_problemDetail_on_IllegalStateException() {
        CreateTodolistCommand command = new CreateTodolistCommand(null);
        webTestClient.post()
                     .uri("/todolist")
                     .body(Mono.just(command), CreateTodolistCommand.class)
                     .exchange()
                     .expectStatus()
                     .isEqualTo(HttpStatus.BAD_REQUEST.value())
                     .expectBody()
                     .jsonPath("$.detail")
                     .isEqualTo("name: must not be blank");
    }

    @Test
    void should_return_create_todolist() {
        CreateTodolistCommand command = new CreateTodolistCommand("My todolist");
        webTestClient.post()
                     .uri("/todolist")
                     .body(Mono.just(command), CreateTodolistCommand.class)
                     .exchange()
                     .expectStatus()
                     .isEqualTo(HttpStatus.OK.value())
                     .expectBody()
                     .jsonPath("$.name")
                     .isEqualTo("My todolist")
                     .jsonPath("$.id")
                     .isNotEmpty();
    }
}
