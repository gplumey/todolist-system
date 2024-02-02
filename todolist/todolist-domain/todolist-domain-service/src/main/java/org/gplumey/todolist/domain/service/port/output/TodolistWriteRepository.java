package org.gplumey.todolist.domain.service.port.output;

import org.gplumey.todolist.domain.core.entity.Todolist;

public interface TodolistWriteRepository {

    Todolist save(Todolist todolist);

    void delete(Todolist todolist);
}
