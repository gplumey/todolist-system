package org.gplumey.todolist.domain.service.validation;


import jakarta.validation.ConstraintViolationException;
import org.gplumey.common.domain.core.usecase.Usecase;

public interface UsecaseValidator {


    <T> void validate(T usecase) throws ConstraintViolationException, IllegalArgumentException;
}
