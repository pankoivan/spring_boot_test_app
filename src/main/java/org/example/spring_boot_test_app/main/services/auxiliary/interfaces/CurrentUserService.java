package org.example.spring_boot_test_app.main.services.auxiliary.interfaces;

import org.example.spring_boot_test_app.main.entities.AppUser;

public interface CurrentUserService {

    AppUser appUser();

    boolean isAnonymous();

    boolean isUser();

    boolean isAdmin();

}
