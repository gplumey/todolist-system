package org.gplumey.common.domain.core.usecase;

public interface QueryHandler<R, Q> extends Usecase {

    R request(Q query);
}
