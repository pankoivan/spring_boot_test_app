package org.example.spring_boot_test_app.main.services.auxiliary.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CurrentUserServiceImplTest {

    static AppUser appUser(Role role) {
        return AppUser
                .builder()
                .username("testMail@mail")
                .password("123")
                .role(role)
                .build();
    }

    static void setAuthentication(AppUser appUser) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(appUser, appUser.getUsername(), appUser.getAuthorities())
        );
    }

    static void setUnexpectedClassAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(new Object(), "credentials")
        );
    }

    static void setAnonymousAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(
                new AnonymousAuthenticationToken("key", "credentials", List.of(Role.ADMIN))
        );
    }

    private final CurrentUserServiceImpl service = new CurrentUserServiceImpl();

    @Test
    void testAppUser_successCase() {
        AppUser appUser = appUser(Role.USER);
        setAuthentication(appUser);
        assertThat(service.appUser()).isEqualTo(appUser);
    }

    @Test
    void testAppUser_throwsWhenNullCase() {
        assertThatThrownBy(service::appUser).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testAppUser_throwsWhenUnexpectedClassCase() {
        setUnexpectedClassAuthentication();
        assertThatThrownBy(service::appUser).isInstanceOf(ClassCastException.class);
    }

    @Test
    void testIsAnonymous_trueWhenNullCase() {
        assertThat(service.isAnonymous()).isTrue();
    }

    @Test
    void testIsAnonymous_trueWhenAnonymousCase() {
        setAnonymousAuthentication();
        assertThat(service.isAnonymous()).isTrue();
    }

    @Test
    void testIsAnonymous_falseCase() {
        AppUser appUser = appUser(Role.USER);
        setAuthentication(appUser);
        assertThat(service.isAnonymous()).isFalse();
    }

    @Test
    void testIsUser_trueCase() {
        AppUser appUser = appUser(Role.USER);
        setAuthentication(appUser);
        assertThat(service.isUser()).isTrue();
    }

    @Test
    void testIsUser_falseWhenAdminCase() {
        AppUser appUser = appUser(Role.ADMIN);
        setAuthentication(appUser);
        assertThat(service.isUser()).isFalse();
    }

    @Test
    void testIsUser_falseWhenNullContextCase() {
        assertThat(service.isUser()).isFalse();
    }

    @Test
    void testIsUser_falseWhenUnexpectedClassInContextCase() {
        setUnexpectedClassAuthentication();
        assertThat(service.isUser()).isFalse();
    }

    @Test
    void testIsAdmin_trueCase() {
        AppUser appUser = appUser(Role.ADMIN);
        setAuthentication(appUser);
        assertThat(service.isAdmin()).isTrue();
    }

    @Test
    void testIsAdmin_falseWhenUserCase() {
        AppUser appUser = appUser(Role.USER);
        setAuthentication(appUser);
        assertThat(service.isAdmin()).isFalse();
    }

    @Test
    void testIsAdmin_falseWhenNullContextCase() {
        assertThat(service.isAdmin()).isFalse();
    }

    @Test
    void testIsAdmin_falseWhenUnexpectedClassInContextCase() {
        setUnexpectedClassAuthentication();
        assertThat(service.isAdmin()).isFalse();
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

}
