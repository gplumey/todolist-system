package org.gplumey.todolist.domain.core.entity;


import lombok.Builder;
import lombok.Getter;
import org.gplumey.common.domain.core.entity.AggregateRoot;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodoLabel;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.core.execption.TodoLimitExceededTodolistException;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Getter
public class Todolist extends AggregateRoot<TodolistId> {

    private static final int MAX_TODO_COUNT = 100;
    private TodolistName name;
    private List<Todo> todos;

    @Builder
    public Todolist(TodolistId id, TodolistName name, List<Todo> todos) {
        super(id);
        this.name = name;
        this.todos = todos == null ? new LinkedList<>() : new LinkedList<>(todos);
    }

    public static Todolist create(String name) {
        return Todolist.builder()
                       .id(TodolistId.create())
                       .name(TodolistName.of(name))
                       .build();
    }

    public Todo addTodo(String label) {
        if (todos.size() >= MAX_TODO_COUNT) {
            throw new TodoLimitExceededTodolistException(id, MAX_TODO_COUNT);
        }
        Todo newTodo = Todo.builder()
                           .todolistId(id)
                           .id(TodoId.create())
                           .label(TodoLabel.of(label))
                           .build();
        todos.add(newTodo);
        return newTodo;
    }

    public Collection<Todo> getTodos() {
        return Collections.unmodifiableList(todos);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
