package org.gplumey.importer.domein.service.port.output;

import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domain.core.entity.valueobject.ImportId;

import java.util.Optional;

public interface Imports {


    void save(Import imp);

    Optional<Import> fingById(ImportId importId);
}
