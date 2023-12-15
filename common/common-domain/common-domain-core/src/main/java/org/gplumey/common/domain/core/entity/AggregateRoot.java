package org.gplumey.common.domain.core.entity;

public class AggregateRoot<ID> extends BaseEntity<ID> {
    public AggregateRoot(ID id) {
        super(id);
    }
}
