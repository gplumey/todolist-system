package org.gplumey.todolist.domain.core.entity.valueobject;


import java.util.Objects;

import static org.gplumey.common.domain.core.util.Assert.notBlank;


public record TodolistName(String value) {

    public TodolistName(String value) {
        this.value = notBlank(value, "value for TodolistName must not be blank");
    }

    public static TodolistName of(String name) {
        return new TodolistName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodolistName that = (TodolistName) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
