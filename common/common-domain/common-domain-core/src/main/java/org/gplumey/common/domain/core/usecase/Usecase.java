package org.gplumey.common.domain.core.usecase;

import java.util.Set;

public interface Usecase {


    static void fail(String message, Set<ValidationError> validationErrors) {
        throw new UsecaseValidationException(message, validationErrors);
    }
}
