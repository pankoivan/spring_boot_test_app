package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {},
        callSuper = true
)
@SuperBuilder
public class Product {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", unique = true, nullable = false, columnDefinition = "TEXT")
    private String description;

}
