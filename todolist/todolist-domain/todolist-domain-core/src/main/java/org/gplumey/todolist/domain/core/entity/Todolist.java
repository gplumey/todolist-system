package org.gplumey.todolist.domain.core.entity;


import lombok.Builder;
import lombok.Getter;
import org.gplumey.common.domain.core.entity.AggregateRoot;
import org.gplumey.todolist.domain.core.entity.valueobject.TaskId;
import org.gplumey.todolist.domain.core.entity.valueobject.TaskLabel;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistId;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;
import org.gplumey.todolist.domain.core.execption.TaskLimitExceededTodolistException;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Getter
public class Todolist extends AggregateRoot<TodolistId> {

    private static final int MAX_TASK_COUNT = 100;
    private TodolistName name;
    private List<Task> tasks;

    @Builder
    public Todolist(TodolistId id, TodolistName name, List<Task> tasks) {
        super(id);
        this.name = name;
        this.tasks = tasks == null ? new LinkedList<>() : new LinkedList<>(tasks);
    }

    public static Todolist create(String name) {
        return Todolist.builder()
                       .id(TodolistId.create())
                       .name(TodolistName.of(name))
                       .build();
    }

    public Task addTask(String label) {
        if (tasks.size() >= MAX_TASK_COUNT) {
            throw new TaskLimitExceededTodolistException(MAX_TASK_COUNT);
        }
        Task newTask = Task.builder()
                           .todolistId(id)
                           .id(TaskId.create())
                           .label(TaskLabel.of(label))
                           .build();
        tasks.add(newTask);
        return newTask;
    }

    public Collection<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
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
