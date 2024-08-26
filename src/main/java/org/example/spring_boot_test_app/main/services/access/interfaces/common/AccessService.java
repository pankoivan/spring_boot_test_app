package org.example.spring_boot_test_app.main.services.access.interfaces.common;

import org.example.spring_boot_test_app.main.exceptions.AccessRightsException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface AccessService<T> {

    default Set<T> availableOnly(Set<T> entities) {
        return entities
                .stream()
                .filter(this::canRead)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    boolean canRead(T entity);

    default void shouldRead(T entity) {
        if (!canRead(entity)) {
            throw new AccessRightsException("Недостаточно прав для чтения");
        }
    }

    boolean canCreate();

    default void shouldCreate() {
        if (!canCreate()) {
            throw new AccessRightsException("Недостаточно прав для создания");
        }
    }

    boolean canEdit(T entity);

    default void shouldEdit(T entity) {
        if (!canEdit(entity)) {
            throw new AccessRightsException("Недостаточно прав для редактирования");
        }
    }

    boolean canDelete(T entity);

    default void shouldDelete(T entity) {
        if (!canDelete(entity)) {
            throw new AccessRightsException("Недостаточно прав для удаления");
        }
    }

}
