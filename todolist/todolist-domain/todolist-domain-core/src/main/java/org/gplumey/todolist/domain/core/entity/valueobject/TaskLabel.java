package org.gplumey.todolist.domain.core.entity.valueobject;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static org.gplumey.common.domain.core.util.Assert.notBlank;

@EqualsAndHashCode
@Getter
@ToString
public class TaskLabel {

    private final String value;

    public TaskLabel(String value) {
        this.value = notBlank(value, "value for TaskName must not be blank");
    }

    public static TaskLabel of(String name){
        return new TaskLabel(name);
    }

}
