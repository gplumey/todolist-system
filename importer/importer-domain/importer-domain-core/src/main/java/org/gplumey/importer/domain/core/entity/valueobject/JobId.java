package org.gplumey.importer.domain.core.entity.valueobject;

import org.gplumey.common.domain.core.entity.BaseId;

import java.util.UUID;

public class JobId extends BaseId<UUID> {
    protected JobId(UUID value) {
        super(value);
    }


    public static JobId create() {
        return new JobId(UUID.randomUUID());
    }
}
