package org.example.spring_boot_test_app.main.entities.common;

import java.time.LocalDateTime;

public interface BaseEntity {

    Integer getId();

    LocalDateTime getCreationDate();

    LocalDateTime getEditingDate();

    boolean equals(Object o);

    int hashCode();

}
