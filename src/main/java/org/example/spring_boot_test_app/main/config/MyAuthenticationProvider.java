package org.example.spring_boot_test_app.main.config;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final AppUserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        AppUser appUser = repository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("Неверный логин"));

        if (passwordEncoder.matches(password, appUser.getPassword())) {
            return new UsernamePasswordAuthenticationToken(appUser, password, appUser.getAuthorities());
        } else {
            throw new BadCredentialsException("Неверный пароль");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
