package org.gplumey.todolist.domain.core.entity;

import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodolistTest {

    @Test
    void shouldBuildWithName(){
        Todolist todolist = Todolist.builder().id(TodolistId.create()).name(TodolistName.of("My first Todolist")).build();

        assertEquals("My first Todolist", todolist.getName().getValue());
    }


}