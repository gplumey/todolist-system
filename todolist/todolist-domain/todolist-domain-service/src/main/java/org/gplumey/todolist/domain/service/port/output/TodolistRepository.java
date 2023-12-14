package org.gplumey.todolist.domain.service.port.output;

import org.gplumey.todolist.domain.core.entity.Todolist;

public interface TodolistRepository  {

    Iterable<Todolist> findAll();

}
