package org.gplumey.todolist.dataaccess;

import org.assertj.core.api.Assertions;
import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest
class TodolistRepositoryImplTest {


    @Autowired
    TodolistReadRepository readRepository;

    @Test
    void findAll() {
        List<Todolist> result = StreamSupport.stream(readRepository.findAll().spliterator(), false)
                                             .collect(Collectors.toList());
        Assertions.assertThat(result).hasSize(1).extracting(todolist -> todolist.getName().value()).containsExactlyInAnyOrder("first todolist");
    }
}
