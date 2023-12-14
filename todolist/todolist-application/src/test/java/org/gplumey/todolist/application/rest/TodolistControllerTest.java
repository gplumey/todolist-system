package org.gplumey.todolist.application.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodolistControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void list() throws Exception {
        webTestClient.get().uri("/todolist").exchange().expectStatus().isOk()
                     .expectBody().jsonPath("$.length()").isEqualTo(1)
                     .jsonPath("$[0].name").isEqualTo("Default todolist");
    }
}