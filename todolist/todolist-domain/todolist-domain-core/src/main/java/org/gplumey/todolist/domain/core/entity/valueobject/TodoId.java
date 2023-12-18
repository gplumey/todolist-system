package org.gplumey.todolist.domain.core.entity.valueobject;

import org.gplumey.common.domain.core.entity.BaseId;

import java.util.UUID;

public class TodoId extends BaseId<UUID> {
    protected TodoId(UUID value) {
        super(value);
    }

    public static TodoId create() {
        return new TodoId(UUID.randomUUID());
    }

    public static TodoId of(UUID uuid) {
        return new TodoId(uuid);
    }
}
