package org.example.spring_boot_test_app.main.services.base.implementations;

import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserAddingDto;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserEditingDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.springframework.validation.BindingResult;

import java.util.Set;

public class AppUserService implements BaseService<AppUser, AppUserAddingDto, AppUserEditingDto> {

    @Override
    public AppUser findById(Integer id) {
        return null;
    }

    @Override
    public Set<AppUser> findAll() {
        return Set.of();
    }

    @Override
    public AppUser add(AppUserAddingDto addingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public AppUser edit(AppUserEditingDto editingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
