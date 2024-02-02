package org.gplumey.importer.domain.core.entity;

import org.gplumey.common.domain.core.entity.BaseEntity;
import org.gplumey.importer.domain.core.entity.valueobject.FilePath;
import org.gplumey.importer.domain.core.entity.valueobject.ImportFileId;

import java.io.File;

public class ImportFile extends BaseEntity<ImportFileId> {

    final FilePath path;

    protected ImportFile(ImportFileId id, FilePath path) {
        super(id);
        this.path = path;
    }


    public static ImportFile of(File file) {
        return new ImportFile(ImportFileId.create(), FilePath.of(file));
    }

    public FilePath getPath() {
        return path;
    }
}
