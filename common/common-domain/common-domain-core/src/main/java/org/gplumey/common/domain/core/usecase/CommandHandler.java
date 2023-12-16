package org.gplumey.common.domain.core.usecase;

public interface CommandHandler<D, C> extends Usecase {

    D execute(C command);
}
