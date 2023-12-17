package org.gplumey.todolist.domain.core.entity.valueobject;


import lombok.Getter;

import static org.gplumey.common.domain.core.util.Assert.notBlank;


@Getter
public class TodolistName {

    private final String value;

    public TodolistName(String value) {
        this.value = notBlank(value, "value for TodolistName must not be blank");
    }

    public static TodolistName of(String name) {
        return new TodolistName(name);
    }
}
