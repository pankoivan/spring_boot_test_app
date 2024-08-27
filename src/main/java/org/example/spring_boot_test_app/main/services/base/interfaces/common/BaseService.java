package org.example.spring_boot_test_app.main.services.base.interfaces.common;

import org.springframework.validation.BindingResult;

import java.util.Set;

public interface BaseService<T, A, E> {

    T findById(Integer id);

    Set<T> findAll();

    T add(A addingDto, BindingResult bindingResult);

    T edit(E editingDto, BindingResult bindingResult);

    void deleteById(Integer id);

}
