package org.example.spring_boot_test_app.main.config;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.example.spring_boot_test_app.main.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MyAuthenticationProviderTest {

    private final AppUser admin = AppUser
            .builder()
            .username("testMail@mail")
            .password("123")
            .role(Role.ADMIN)
            .build();

    private final String incorrectUsername = admin.getUsername().concat("1");

    private final String incorrectPassword = admin.getPassword().concat("1");

    @Mock
    private AppUserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MyAuthenticationProvider provider;

    @Test
    void testAuthenticate_successCase() {
        doReturn(Optional.of(admin)).when(repository).findByUsername(admin.getUsername());
        doReturn(true).when(passwordEncoder).matches(admin.getPassword(), admin.getPassword());
        Authentication sourceAuthentication = new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword());
        Authentication resultAuthentication = new UsernamePasswordAuthenticationToken(admin, admin.getPassword(), admin.getAuthorities());
        assertThat(provider.authenticate(sourceAuthentication)).isEqualTo(resultAuthentication);
    }

    @Test
    void testAuthenticate_incorrectUsernameFailCase() {
        doReturn(Optional.empty()).when(repository).findByUsername(incorrectUsername);
        Authentication sourceAuthentication = new UsernamePasswordAuthenticationToken(incorrectUsername, admin.getPassword());
        assertThatThrownBy(() -> provider.authenticate(sourceAuthentication))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("Неверный логин");
    }

    @Test
    void testAuthenticate_incorrectPasswordFailCase() {
        doReturn(Optional.of(admin)).when(repository).findByUsername(admin.getUsername());
        doReturn(false).when(passwordEncoder).matches(incorrectPassword, admin.getPassword());
        Authentication sourceAuthentication = new UsernamePasswordAuthenticationToken(admin.getUsername(), incorrectPassword);
        assertThatThrownBy(() -> provider.authenticate(sourceAuthentication))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Неверный пароль");
    }

    @Test
    void testSupports_trueCase() {
        assertThat(provider.supports(UsernamePasswordAuthenticationToken.class)).isTrue();
    }

    @Test
    void testSupports_falseCase() {
        assertThat(provider.supports(RememberMeAuthenticationProvider.class)).isFalse();
    }

    @Test
    void testSupports_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> provider.supports(null));
    }

}
