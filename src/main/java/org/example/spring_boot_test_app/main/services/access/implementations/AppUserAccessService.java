package org.example.spring_boot_test_app.main.services.access.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.services.access.interfaces.common.AccessService;
import org.example.spring_boot_test_app.main.services.auxiliary.interfaces.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class AppUserAccessService implements AccessService<AppUser> {

    private CurrentUserService currentUserService;

    @Override
    public boolean canRead(AppUser appUser) {
        return !currentUserService.isAnonymous();
    }

    @Override
    public boolean canCreate() {
        return currentUserService.isAnonymous();
    }

    @Override
    public boolean canEdit(AppUser appUser) {
        return currentUserService.isAdmin() || currentUserService.appUser().equals(appUser);
    }

    @Override
    public boolean canDelete(AppUser appUser) {
        return currentUserService.isAdmin() && !currentUserService.appUser().equals(appUser);
    }

}
