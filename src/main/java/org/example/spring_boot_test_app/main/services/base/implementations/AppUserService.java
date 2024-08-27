package org.example.spring_boot_test_app.main.services.base.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserAddingDto;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserEditingDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.exceptions.EntityNoFoundException;
import org.example.spring_boot_test_app.main.repository.AppUserRepository;
import org.example.spring_boot_test_app.main.services.access.implementations.AppUserAccessService;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.example.spring_boot_test_app.main.services.validation.implementations.AppUserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class AppUserService implements BaseService<AppUser, AppUserAddingDto, AppUserEditingDto> {

    private final AppUserRepository repository;

    private AppUserAccessService accessService;

    private AppUserValidationService validationService;

    @Override
    public AppUser findById(Integer id) {
        AppUser appUser = repository.findById(id).orElseThrow(() -> new EntityNoFoundException("Пользователь с id \"%s\" не найден"));
        accessService.shouldRead(appUser);
        return appUser;
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
