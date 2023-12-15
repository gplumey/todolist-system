package org.gplumey.common.domain.core.usecase;

public interface QueryHandler<Q, R> extends Usecase {

    R requend(Q query);
}
