package org.example.spring_boot_test_app.main.entities;

import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class AppUserTest {

    @ParameterizedTest
    @EnumSource(Role.class)
    void testGetAuthorities_successCase(Role role) {
        AppUser appUser = new AppUser();
        appUser.setRole(role);
        assertThat(appUser.getAuthorities()).isEqualTo(Collections.singletonList(role));
    }

}
