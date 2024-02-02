package org.gplumey.importer.infra.batch.model;

public class TodolistRow {
    String name;

    public TodolistRow() {
    }

    public TodolistRow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
