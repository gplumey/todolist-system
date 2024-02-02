package org.gplumey.importer.infra.data;

import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domain.core.entity.valueobject.ImportId;
import org.gplumey.importer.domein.service.port.output.Imports;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ImportRepository implements Imports {

    private static final Map<ImportId, Import> map = new HashMap<>();

    @Override
    public void save(Import imp) {
        map.put(imp.getId(), imp);
    }

    @Override
    public Optional<Import> fingById(ImportId importId) {
        return Optional.of(map.get(importId));
    }
}
