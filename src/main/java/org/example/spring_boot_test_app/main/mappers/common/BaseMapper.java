package org.example.spring_boot_test_app.main.mappers.common;

public interface BaseMapper<T, M> {

    M map(T entity);

}
