package org.gplumey.importer.domein.service.impl;

import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domein.service.port.input.Usecases;
import org.gplumey.importer.domein.service.port.input.command.StartImportCommand;
import org.gplumey.importer.domein.service.port.output.ImporterService;
import org.gplumey.importer.domein.service.port.output.Imports;
import org.springframework.stereotype.Component;

@Component
public class StartImportUsecase implements Usecases.Commands.StartImport {

    private final Imports imports;
    private final ImporterService importerService;

    public StartImportUsecase(Imports imports, ImporterService importerService) {
        this.imports = imports;
        this.importerService = importerService;
    }

    @Override
    public Import execute(StartImportCommand command) {
        var imp = imports.fingById(command.importId()).orElseGet(() -> {
            throw new RuntimeException("Import not found exception");
        });

        imp.start();
        importerService.execute(imp);
        return imp;
    }
}
