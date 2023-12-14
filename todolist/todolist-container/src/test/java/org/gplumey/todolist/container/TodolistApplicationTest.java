package org.gplumey.todolist.container;


import org.junit.jupiter.api.Test;

class TodolistApplicationTest {

    @Test
    void givenNoArgs_whenMain_thenStart() {
        String[] args = new String[0];
        TodolistApplication.main(args);
    }
}