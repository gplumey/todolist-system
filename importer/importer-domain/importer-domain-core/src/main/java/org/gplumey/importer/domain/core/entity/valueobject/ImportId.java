package org.gplumey.importer.domain.core.entity.valueobject;

import org.gplumey.common.domain.core.entity.BaseId;

import java.util.UUID;

public class ImportId extends BaseId<UUID> {
    protected ImportId(UUID value) {
        super(value);
    }

    public static ImportId create() {
        return new ImportId(UUID.randomUUID());
    }

    public static ImportId of(String uuid) {
        return new ImportId(UUID.fromString(uuid));
    }

    public static ImportId of(UUID uuid) {
        return new ImportId(uuid);
    }
}
