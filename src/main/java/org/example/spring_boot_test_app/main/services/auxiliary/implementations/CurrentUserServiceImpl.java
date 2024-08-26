package org.example.spring_boot_test_app.main.services.auxiliary.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.example.spring_boot_test_app.main.services.auxiliary.interfaces.CurrentUserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    @Override
    public AppUser appUser() {
        return (AppUser) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
    }

    @Override
    public boolean isAnonymous() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    @Override
    public boolean isUser() {
        try {
            return appUser().getRole() == Role.USER;
        } catch (NullPointerException | ClassCastException e) {
            return false;
        }
    }

    @Override
    public boolean isAdmin() {
        try {
            return appUser().getRole() == Role.ADMIN;
        } catch (NullPointerException | ClassCastException e) {
            return false;
        }
    }

}
