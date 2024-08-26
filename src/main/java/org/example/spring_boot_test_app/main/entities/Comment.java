package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {},
        callSuper = true
)
@SuperBuilder
public class Comment {

    @Column(name = "text", unique = true, nullable = false, columnDefinition = "TEXT")
    private String text;

}
