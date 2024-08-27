package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.spring_boot_test_app.main.entities.common.AbstractBaseEntity;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {"product", "author"},
        callSuper = true
)
@SuperBuilder
public class Comment extends AbstractBaseEntity {

    @Column(name = "text", unique = true, nullable = false, columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AppUser author;

}
