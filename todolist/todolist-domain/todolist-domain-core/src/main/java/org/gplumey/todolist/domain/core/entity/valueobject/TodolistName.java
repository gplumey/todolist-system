package org.gplumey.todolist.domain.core.entity.valueobject;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class TodolistName {

    private final String value;



    public static TodolistName of(String name){
        return new TodolistName(name);
    }

}
