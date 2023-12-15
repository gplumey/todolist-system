package org.gplumey.common.domain.core.entity;

import java.util.Objects;

public class BaseEntity<ID> {

    protected final ID id;

    public BaseEntity(ID id) {
        this.id = Objects.requireNonNull(id);
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
