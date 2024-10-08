package org.example.spring_boot_test_app.main.services.validation.interfaces.common;

import org.example.spring_boot_test_app.main.exceptions.FieldsValidationException;
import org.springframework.validation.BindingResult;

public interface ValidationService<T, S, A, E> {

    default void bindingResultValidation(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new FieldsValidationException(bindingResult.getFieldErrors());
        }
    }

    void savingValidation(S savingDto, BindingResult bindingResult);

    void addingValidation(A addingDto, BindingResult bindingResult);

    T editingValidation(E editingDto, BindingResult bindingResult);

    void deletionValidation(T entity);

}
