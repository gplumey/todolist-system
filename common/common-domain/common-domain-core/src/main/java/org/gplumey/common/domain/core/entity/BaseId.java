package org.gplumey.common.domain.core.entity;

import java.util.Objects;

public class BaseId<T> {

    private final T value;

    protected BaseId(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> T nullSafeValue(BaseId<T> id) {
        return id == null ? null : id.getValue();
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return value.equals(baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BaseId{" + "value=" + value + '}';
    }
}
