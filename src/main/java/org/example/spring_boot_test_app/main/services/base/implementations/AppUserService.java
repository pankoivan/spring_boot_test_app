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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class AppUserService implements BaseService<AppUser, AppUserAddingDto, AppUserEditingDto> {

    private final AppUserRepository repository;

    private final PasswordEncoder passwordEncoder;

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
        return accessService.availableOnly(repository.findAllByOrderByCreationDateDesc());
    }

    @Override
    public AppUser add(AppUserAddingDto addingDto, BindingResult bindingResult) {
        validationService.addingValidation(addingDto, bindingResult);
        accessService.shouldCreate();
        return repository.save(
                AppUser
                        .builder()
                        .username(addingDto.getUsername())
                        .password(passwordEncoder.encode(addingDto.getPassword()))
                        .role(addingDto.getRole())
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public AppUser edit(AppUserEditingDto editingDto, BindingResult bindingResult) {
        AppUser appUser = validationService.editingValidation(editingDto, bindingResult);
        accessService.shouldEdit(appUser);
        appUser.setUsername(editingDto.getUsername());
        appUser.setPassword(passwordEncoder.encode(editingDto.getPassword()));
        appUser.setRole(editingDto.getRole());
        appUser.setEditingDate(LocalDateTime.now());
        return repository.save(appUser);
    }

    @Override
    public void deleteById(Integer id) {
        AppUser appUser = findById(id);
        validationService.deletionValidation(appUser);
        accessService.shouldDelete(appUser);
        repository.deleteById(id);
    }

}
