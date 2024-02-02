package org.gplumey.importer.domein.service.impl;

import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domain.core.entity.ImportFile;
import org.gplumey.importer.domein.service.port.input.Usecases;
import org.gplumey.importer.domein.service.port.input.command.CreateImportCommand;
import org.gplumey.importer.domein.service.port.output.Imports;
import org.springframework.stereotype.Component;


@Component
public class CreateImportUsecase implements Usecases.Commands.CreateImport {

    private final Imports imports;

    public CreateImportUsecase(Imports imports) {
        this.imports = imports;
    }

    @Override
    public Import execute(CreateImportCommand command) {
        var importFile = ImportFile.of(command.importFile());
        var imp = Import.create(importFile);
        imports.save(imp);
        return imp;
    }
}
