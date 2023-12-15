package org.gplumey.todolist.domain.service.port.input;

import org.gplumey.todolist.domain.core.entity.Todolist;

import java.util.List;

public interface TodolistService {


    Iterable<Todolist> readAll();

}
