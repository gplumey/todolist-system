package org.gplumey.importer.domein.service.port.input;

import org.gplumey.common.domain.core.usecase.CommandHandler;
import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domein.service.port.input.command.CreateImportCommand;
import org.gplumey.importer.domein.service.port.input.command.StartImportCommand;

public interface Usecases {


    class Commands {

        public interface CreateImport extends CommandHandler<Import, CreateImportCommand> {
        }

        public interface StartImport extends CommandHandler<Import, StartImportCommand> {
        }
    }
}
