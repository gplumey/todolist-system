package org.gplumey.todolist.domain.core.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.gplumey.todolist.domain.core.entity.valueobject.TodolistName;

@Builder
@Getter
@AllArgsConstructor
public class Todolist {

    private TodolistName name;


}
