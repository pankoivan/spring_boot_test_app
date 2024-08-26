package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {"createdComments", "createdProducts", "createdTags"},
        callSuper = true
)
@SuperBuilder
public class AppUser implements UserDetails {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    @Builder.Default
    private Set<Comment> createdComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "author")
    @Builder.Default
    private Set<Product> createdProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "author")
    @Builder.Default
    private Set<Tag> createdTags = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
