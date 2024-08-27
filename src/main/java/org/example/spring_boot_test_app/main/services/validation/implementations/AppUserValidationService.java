package org.example.spring_boot_test_app.main.services.validation.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserAddingDto;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserEditingDto;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserSavingDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.exceptions.InputValidationException;
import org.example.spring_boot_test_app.main.repository.AppUserRepository;
import org.example.spring_boot_test_app.main.services.validation.interfaces.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class AppUserValidationService implements ValidationService<AppUser, AppUserSavingDto, AppUserAddingDto, AppUserEditingDto> {

    private final AppUserRepository repository;

    private AppUserService service;

    @Override
    public void savingValidation(AppUserSavingDto savingDto, BindingResult bindingResult) {
        bindingResultValidation(bindingResult);
    }

    @Override
    public void addingValidation(AppUserAddingDto addingDto, BindingResult bindingResult) {
        if (repository.existsByUsername(addingDto.getUsername())) {
            bindingResult.addError(new FieldError("existsByUsername", "username", "Такое имя пользователя уже существует"));
        }
        savingValidation(addingDto, bindingResult);
    }

    @Override
    public AppUser editingValidation(AppUserEditingDto editingDto, BindingResult bindingResult) {
        AppUser appUser = service.findById(editingDto.getId());
        if (repository.existsByUsername(editingDto.getUsername()) && !appUser.getUsername().equals(editingDto.getUsername())) {
            bindingResult.addError(new FieldError("existsByUsername", "username", "Такое имя пользователя уже существует"));
        }
        savingValidation(editingDto, bindingResult);
        return appUser;
    }

    @Override
    public void deletionValidation(AppUser appUser) {
        if (!appUser.getCreatedComments().isEmpty() || !appUser.getCreatedProducts().isEmpty() || !appUser.getCreatedTags().isEmpty()) {
            throw new InputValidationException(
                    "Этот пользователь не может быть удалён, так как в приложении есть его комментарии, товары или теги"
            );
        }
    }

}
