package org.example.spring_boot_test_app.main.entities.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Role implements GrantedAuthority {

    USER,

    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }

}
