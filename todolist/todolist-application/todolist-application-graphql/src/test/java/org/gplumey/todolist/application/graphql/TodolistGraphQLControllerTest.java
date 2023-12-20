package org.gplumey.todolist.application.graphql;

import org.gplumey.todolist.application.graphql.schema.generated.TodolistGraphQL;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.output.TodolistEventPublisher;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TodolistGraphQLControllerTest {
    @Autowired
    WebApplicationContext
            context;
    @MockBean
    TodolistEventPublisher todolistEventPublisher;
    @MockBean
    TodolistWriteRepository writeRepository;
    @MockBean
    TodolistReadRepository readRepository;
    private GraphQlTester tester;

    @BeforeEach
    void setup() {
        reset(readRepository, writeRepository);
        when(writeRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        WebTestClient client =
                MockMvcWebTestClient.bindToApplicationContext(context)
                                    .configureClient()
                                    .baseUrl("/graphql")
                                    .build();

        tester = HttpGraphQlTester.create(client);
    }

    @Test
    void should_query_todolist() {
        String defaultTodoListName = "Default todolist";
        Collection<Todolist> mockData = List.of(
                Todolist.builder().id(TodolistId.of("aa876b9b-1400-49dd-9c56-e594679949e6")).name(TodolistName.of(defaultTodoListName)).build());
        when(readRepository.findAll()).thenReturn(mockData);
        String document = "query {\n" +
                "    todolists {\n" +
                "        id\n" +
                "        name\n" +
                "        todos {\n" +
                "            id\n" +
                "            label\n" +
                "        }\n" +
                "    }\n" +
                "}";
        List<TodolistGraphQL> todolistGraphQL = tester.document(document)
                                                      .execute()
                                                      .path("todolists")
                                                      .entityList(TodolistGraphQL.class).get();
        Assertions.assertEquals("Default todolist", todolistGraphQL.get(0).getName());
        Assertions.assertEquals("aa876b9b-1400-49dd-9c56-e594679949e6", todolistGraphQL.get(0).getId());
    }
}
