package org.gplumey.todolist.domain.service.port.usecase.create;

import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.input.GetAllTodolistUseCase;
import org.gplumey.todolist.domain.service.port.input.query.GetAllTodolistQuery;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetAllTodolistUseCaseTest {


    @Autowired
    GetAllTodolistUseCase useCase;

    @Autowired
    TodolistReadRepository readRepository;

    Collection<Todolist> mockList = List.of(Todolist.builder().id(TodolistId.create()).name(TodolistName.of("todolist 1")).build(),
            Todolist.builder().id(TodolistId.create()).name(TodolistName.of("todolist 2")).build());

    @BeforeEach
    void setup() {
        reset(readRepository);
        LocaleContextHolder.setLocale(Locale.US);
        when(readRepository.findAll()).thenReturn(mockList);
    }

    @Test
    void should_return_all_todolist_given_valid_request() {
        Iterable<Todolist> todolists = useCase.request(new GetAllTodolistQuery());
        Collection<String> todolistNames = StreamSupport.stream(todolists.spliterator(), true)
                                                        .map(todolist -> todolist.getName().getValue())
                                                        .collect(Collectors.toList());
        assertThat(todolistNames, hasItems("todolist 1", "todolist 2"));
    }
}
