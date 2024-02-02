package org.gplumey.importer.domain.core.entity;

import org.gplumey.common.domain.core.entity.AggregateRoot;
import org.gplumey.common.domain.core.eventing.DomainEvent;
import org.gplumey.importer.domain.core.entity.valueobject.ImportId;
import org.gplumey.importer.domain.core.entity.valueobject.ImportStatus;

import java.time.Instant;

public class Import extends AggregateRoot<ImportId, DomainEvent<Import>> {
    private final ImportFile importFile;
    private ImportStatus status;
    private Instant startTime;

    public Import(ImportId id, ImportFile importFile, ImportStatus status) {
        super(id);
        this.importFile = importFile;
        this.status = status;
    }


    public static Import create(ImportFile importFile) {
        return new Import(ImportId.create(), importFile, ImportStatus.PENDING);
    }


    public void start() {
        this.status = ImportStatus.STARTED;
        startTime = Instant.now();
    }


    public ImportFile getImportFile() {
        return importFile;
    }
}
