package org.gplumey.importer.domein.service.port.input.command;

import org.gplumey.importer.domain.core.entity.valueobject.ImportId;

public record StartImportCommand(ImportId importId) {
}
