package org.gplumey.todolist.domain.core.entity.valueobject;


import lombok.Getter;

import static org.gplumey.common.domain.core.util.Assert.notBlank;


@Getter
public class TodoLabel {

    private final String value;

    public TodoLabel(String value) {
        this.value = notBlank(value, "value for TodoLabel must not be blank");
    }

    public static TodoLabel of(String name) {
        return new TodoLabel(name);
    }
}
