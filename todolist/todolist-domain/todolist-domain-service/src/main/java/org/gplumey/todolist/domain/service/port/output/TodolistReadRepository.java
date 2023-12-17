package org.gplumey.todolist.domain.service.port.output;

import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;

import java.util.Collection;
import java.util.Optional;

public interface TodolistReadRepository {

    Collection<Todolist> findAll();

    Todolist save(Todolist todolist);

    Optional<Todolist> get(TodolistId id);
}
