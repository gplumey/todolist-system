package org.gplumey.todolist.domain.core.entity.valueobject;

import org.gplumey.common.domain.core.entity.BaseId;

import java.util.UUID;

public class TodolistId extends BaseId<UUID> {
    protected TodolistId(UUID value) {
        super(value);
    }

    public static TodolistId of(String uuid) {
        return new TodolistId(UUID.fromString(uuid));
    }

    public static TodolistId create() {
        return new TodolistId(UUID.randomUUID());
    }
}
