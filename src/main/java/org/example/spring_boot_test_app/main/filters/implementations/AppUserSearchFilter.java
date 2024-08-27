package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.auxiliary.SearchUtils;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.filters.interfaces.common.SearchFilter;
import org.springframework.stereotype.Component;

@Component
public class AppUserSearchFilter implements SearchFilter<AppUser> {

    @Override
    public boolean matches(AppUser appUser, String search) {
        return SearchUtils.biDirectionalContainsIgnoreCase(appUser.getUsername(), search);
    }

}
