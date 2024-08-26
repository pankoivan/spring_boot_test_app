package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {},
        callSuper = true
)
@SuperBuilder
public class Tag {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
