package org.gplumey.todolist.domain.service.cucumber;

import io.cucumber.java.en.Then;
import org.gplumey.todolist.domain.core.event.TodoCreatedEvent;
import org.gplumey.todolist.domain.core.event.TodoListCreatedEvent;
import org.gplumey.todolist.domain.core.event.TodolistDeletedEvent;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;

public class SharedEventingStepsDefinitions {
    @Autowired
    ApplicationEvents events;

    private Class getEventClass(String event) {
        return switch (event) {
            case "Todolist Created" -> TodoListCreatedEvent.class;
            case "Todo Created" -> TodoCreatedEvent.class;
            case "Todolist Deleted" -> TodolistDeletedEvent.class;
            default -> throw new IllegalStateException("Unexpected value: " + event);
        };
    }

    @Then("{string} event occurs")
    public void event_occurs(String event) {
        long numEvents = events.stream(getEventClass(event)).count();
        Assertions.assertEquals(1, numEvents);
    }

    @Then("{string} event does not occurs")
    public void event_does_not_occurs(String event) {
        long numEvents = events.stream(getEventClass(event)).count();
        Assertions.assertEquals(0, numEvents);
    }
}
