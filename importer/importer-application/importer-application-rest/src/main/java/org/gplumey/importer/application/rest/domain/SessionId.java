package org.gplumey.importer.application.rest.domain;

import java.util.UUID;

public record SessionId(UUID value) {


    public static SessionId create() {
        return new SessionId(UUID.randomUUID());
    }
}
