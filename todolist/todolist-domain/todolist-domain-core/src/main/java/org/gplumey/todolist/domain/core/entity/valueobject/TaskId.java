package org.gplumey.todolist.domain.core.entity.valueobject;

import org.gplumey.common.domain.core.entity.BaseId;

import java.util.UUID;

public class TaskId extends BaseId<UUID> {
    protected TaskId(UUID value) {
        super(value);
    }

    public static TaskId create() {
        return new TaskId(UUID.randomUUID());
    }
}
