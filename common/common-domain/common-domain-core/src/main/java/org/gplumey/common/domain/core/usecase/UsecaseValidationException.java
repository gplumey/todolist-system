package org.gplumey.common.domain.core.usecase;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UsecaseValidationException  extends RuntimeException{

    private final Set<ValidationError> violations;
    public UsecaseValidationException(String message, Set<ValidationError> violations) {
        super(message);
        this.violations = Objects.requireNonNullElseGet(violations, HashSet::new);
    }

    public Set<ValidationError> getViolations() {
        return violations;
    }
}
