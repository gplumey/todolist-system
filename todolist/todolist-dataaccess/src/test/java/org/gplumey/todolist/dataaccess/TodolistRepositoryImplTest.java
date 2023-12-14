package org.gplumey.todolist.dataaccess;

import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.output.TodolistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class TodolistRepositoryImplTest {


    @Autowired
    TodolistRepository todolistRepository;

    @Test
    void findAll() {
        List<Todolist> result = StreamSupport.stream(todolistRepository.findAll().spliterator(), false)
                                                   .collect(Collectors.toList());;
        assertThat(result, contains(hasProperty("name", hasProperty("value", equalTo("first todolist")))));
    }
}