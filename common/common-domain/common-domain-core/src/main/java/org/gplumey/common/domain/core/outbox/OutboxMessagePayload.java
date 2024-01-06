package org.gplumey.common.domain.core.outbox;

import java.time.Instant;
import java.util.UUID;

public interface OutboxMessagePayload {
    Instant getCreatedAt();

    UUID getId();
}
