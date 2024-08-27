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

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AppUser author;

}
