package org.example.spring_boot_test_app.main.services.validation.interfaces;

import org.example.spring_boot_test_app.main.exceptions.InputValidationException;
import org.springframework.validation.BindingResult;

public interface ValidationService<T, S, A, E> {

    default void bindingResultValidation(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new InputValidationException(bindingResult.getFieldErrors().getFirst().getDefaultMessage());
        }
    }

    void savingValidation(S savingDto, BindingResult bindingResult);

    void addingValidation(A addingDto, BindingResult bindingResult);

    T editingValidation(E editingDto, BindingResult bindingResult);

    void deletionValidation(T entity);

}
