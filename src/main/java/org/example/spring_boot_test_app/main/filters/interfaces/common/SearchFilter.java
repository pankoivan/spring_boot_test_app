package org.example.spring_boot_test_app.main.filters.interfaces.common;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface SearchFilter<T> {

    default Set<T> searched(Collection<T> entities, String search) {
        return entities
                .stream()
                .filter(entity -> matches(entity, search))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    boolean matches(T t, String search);

}
