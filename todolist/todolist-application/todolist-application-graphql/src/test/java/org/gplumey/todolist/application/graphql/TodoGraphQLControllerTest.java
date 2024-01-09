package org.gplumey.todolist.application.graphql;

import org.gplumey.todolist.application.graphql.schema.generated.TodoGraphQL;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.eventing.DomainEventPublisher;
import org.gplumey.todolist.domain.service.outbox.OutboxMessageRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TodoGraphQLControllerTest {
    @Autowired
    WebApplicationContext
            context;
    @MockBean(name = DomainEventPublisher.BROKER)
    @Qualifier(DomainEventPublisher.BROKER)
    DomainEventPublisher domainEventPublisher;
    @MockBean
    OutboxMessageRepository outboxMessageRepository;
    @MockBean
    TodolistWriteRepository writeRepository;
    @MockBean
    TodolistReadRepository readRepository;
    GraphQlTester tester;

    @BeforeEach
    void setup() {
        reset(readRepository, writeRepository, domainEventPublisher, outboxMessageRepository);
        when(writeRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        WebTestClient client =
                MockMvcWebTestClient.bindToApplicationContext(context)
                                    .configureClient()
                                    .baseUrl("/graphql")
                                    .build();

        tester = HttpGraphQlTester.create(client);
    }

    @Test
    void should_create_todo() {
        String defaultTodoListName = "Default todolist";
        var mockData = Todolist.builder()
                               .id(TodolistId.of("aa876b9b-1400-49dd-9c56-e594679949e6"))
                               .name(TodolistName.of(defaultTodoListName))
                               .build();
        when(readRepository.findById(any())).thenReturn(Optional.of(mockData));
        String document = "mutation {\n" +
                "    createTodo (createTodoInput: {\n" +
                "        todolistId: \"aa876b9b-1400-49dd-9c56-e594679949e6\"\n" +
                "        label: \"graphql todo\"\n" +
                "    }) {\n" +
                "        id\n" +
                "        label\n" +
                "    }\n" +
                "}";
        TodoGraphQL todolistGraphQL = tester.document(document)
                                            .execute()
                                            .path("createTodo")
                                            .entity(TodoGraphQL.class).get();
        Assertions.assertEquals("graphql todo", todolistGraphQL.getLabel());
        MatcherAssert.assertThat(todolistGraphQL.getId(), is(not(nullValue())));
    }
}
