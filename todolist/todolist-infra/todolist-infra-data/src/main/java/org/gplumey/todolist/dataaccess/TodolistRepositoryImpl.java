package org.gplumey.todolist.dataaccess;

import org.gplumey.todolist.domain.core.entity.Todolist;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.service.port.output.TodolistReadRepository;
import org.gplumey.todolist.domain.service.port.output.TodolistWriteRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TodolistRepositoryImpl implements TodolistWriteRepository, TodolistReadRepository {


    static ConcurrentHashMap<TodolistId, Todolist> map = new ConcurrentHashMap<>();

    static {
        Todolist todolist = Todolist.builder()
                                    .id(TodolistId.create())
                                    .name(TodolistName.of("first todolist"))
                                    .build();
        map.put(todolist.getId(), todolist);
    }

    @Override
    public Collection<Todolist> findAll() {
        return Collections.unmodifiableCollection(map.values());
    }


    @Override
    public Optional<Todolist> get(TodolistId id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Todolist save(Todolist todolist) {
        map.put(todolist.getId(), todolist);
        return todolist;
    }
}
