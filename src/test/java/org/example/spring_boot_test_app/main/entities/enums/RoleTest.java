package org.example.spring_boot_test_app.main.entities.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {

    @ParameterizedTest
    @EnumSource(Role.class)
    void getAuthority(Role role) {
        assertThat(role.getAuthority()).isEqualTo("ROLE_" + role.name());
    }

}
