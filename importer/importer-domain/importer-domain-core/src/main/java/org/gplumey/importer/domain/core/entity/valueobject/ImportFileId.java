package org.gplumey.importer.domain.core.entity.valueobject;

import org.gplumey.common.domain.core.entity.BaseId;

import java.util.UUID;

public class ImportFileId extends BaseId<UUID> {
    protected ImportFileId(UUID value) {
        super(value);
    }

    public static ImportFileId create() {
        return new ImportFileId(UUID.randomUUID());
    }
}
