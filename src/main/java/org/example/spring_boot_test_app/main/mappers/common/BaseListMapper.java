package org.example.spring_boot_test_app.main.mappers.common;

import java.util.Collection;
import java.util.List;

public interface BaseListMapper<T, M> extends BaseMapper<T, M> {

    default List<M> map(Collection<T> entities) {
        return entities == null ? null : entities
                .stream()
                .map(this::map)
                .toList();
    }

}
