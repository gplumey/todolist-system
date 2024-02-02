package org.gplumey.importer.domein.service.port.output;

import org.gplumey.importer.domain.core.entity.Import;
import org.gplumey.importer.domain.core.entity.valueobject.JobId;

public interface ImporterService {

    JobId execute(Import imp);
}
