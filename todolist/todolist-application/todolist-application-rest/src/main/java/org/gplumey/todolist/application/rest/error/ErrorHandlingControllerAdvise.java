package org.gplumey.todolist.application.rest.error;


import jakarta.validation.ConstraintViolationException;
import org.gplumey.todolist.domain.core.execption.TodolistAlreadyExistsException;
import org.gplumey.todolist.domain.core.execption.TodolistNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingControllerAdvise {

    @ExceptionHandler
    ProblemDetail handle(IllegalStateException ise) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
        problemDetail.setDetail(ise.getMessage());
        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail handle(ConstraintViolationException cve) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
        problemDetail.setDetail(cve.getMessage());
        problemDetail.setProperty("errors", cve.getConstraintViolations().stream().map(v -> v.getMessage()));
        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail handle(TodolistNotFoundException nfe) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND.value());
        problemDetail.setDetail(nfe.getMessage());
        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail handle(TodolistAlreadyExistsException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT.value());
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }
}
