package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {"author", "comments", "tags"},
        callSuper = true
)
@SuperBuilder
public class Product {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", unique = true, nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AppUser author;

    @OneToMany(mappedBy = "product")
    @Builder.Default
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false)
    )
    @Builder.Default
    private Set<Tag> tags = new LinkedHashSet<>();

}
