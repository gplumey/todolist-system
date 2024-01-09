package org.gplumey.todolist.domain.service.port.output;

import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;

import java.util.Collection;
import java.util.Optional;

public interface TodolistReadRepository {

    Collection<Todolist> findAll();


    Optional<Todolist> findById(TodolistId id);

    Optional<Todolist> findByName(TodolistName name);
}
