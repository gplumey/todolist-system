package org.gplumey.todolist.domain.service.validation;


import jakarta.validation.ConstraintViolationException;

public interface UsecaseValidator {


    <T> void validate(T usecase) throws ConstraintViolationException, IllegalArgumentException;
}
