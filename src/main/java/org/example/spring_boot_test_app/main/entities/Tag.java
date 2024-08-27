package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.spring_boot_test_app.main.entities.common.AbstractBaseEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {"author", "products"},
        callSuper = true
)
@SuperBuilder
public class Tag extends AbstractBaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AppUser author;

    @ManyToMany(mappedBy = "tags")
    @Builder.Default
    private Set<Product> products = new LinkedHashSet<>();

}
