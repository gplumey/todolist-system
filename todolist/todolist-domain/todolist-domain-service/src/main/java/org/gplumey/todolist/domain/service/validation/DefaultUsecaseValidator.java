package org.gplumey.todolist.domain.service.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class DefaultUsecaseValidator  implements  UsecaseValidator{

    private final Validator validator;

    @Override
    public <T> void validate(T usecase) throws ConstraintViolationException, IllegalArgumentException {
        if(usecase == null ) {
            throw new IllegalArgumentException("Parameter usecase must not be null.");
        }
        Set<ConstraintViolation<T>> violations  =  validator.validate(usecase);
        if(!violations .isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
