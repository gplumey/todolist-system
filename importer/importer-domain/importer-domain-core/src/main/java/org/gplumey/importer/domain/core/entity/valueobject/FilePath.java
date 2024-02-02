package org.gplumey.importer.domain.core.entity.valueobject;

import java.io.File;

public record FilePath(String value) {

    public static FilePath of(File file) {
        return new FilePath(file.getPath());
    }
}
