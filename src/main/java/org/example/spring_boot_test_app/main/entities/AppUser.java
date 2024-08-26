package org.example.spring_boot_test_app.main.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.spring_boot_test_app.main.entities.enums.Role;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(
        exclude = {},
        callSuper = true
)
@SuperBuilder
public class AppUser {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
