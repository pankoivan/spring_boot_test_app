package org.example.spring_boot_test_app.main.services.validation.implementations;

import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserAddingDto;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserEditingDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.example.spring_boot_test_app.main.exceptions.EntityNoFoundException;
import org.example.spring_boot_test_app.main.exceptions.FieldsValidationException;
import org.example.spring_boot_test_app.main.exceptions.InputValidationException;
import org.example.spring_boot_test_app.main.repository.AppUserRepository;
import org.example.spring_boot_test_app.main.services.base.implementations.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.ITERABLE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AppUserValidationServiceTest {

    static BindingResult emptyErrors() {
        return new MapBindingResult(Map.of(), "");
    }

    static BindingResult notEmptyErrors() {
        BindingResult bindingResult = new MapBindingResult(Map.of(), "");
        bindingResult.addError(new FieldError("", "", ""));
        return bindingResult;
    }

    static BindingResult notEmptyErrors(String objectName, String field, String defaultMessage) {
        BindingResult bindingResult = new MapBindingResult(Map.of(), "");
        bindingResult.addError(new FieldError(objectName, field, defaultMessage));
        return bindingResult;
    }

    static AppUserAddingDto appUserAddingDto() {
        AppUserAddingDto addingDto = new AppUserAddingDto();
        addingDto.setUsername("test@mail");
        return addingDto;
    }

    static AppUserEditingDto appUserEditingDto(int id, String username) {
        AppUserEditingDto editingDto = new AppUserEditingDto();
        editingDto.setId(id);
        editingDto.setUsername(username);
        return editingDto;
    }

    static AppUser appUser(int id) {
        return AppUser.builder().id(id).build();
    }

    private final AppUserRepository repository;

    private final AppUserService service;

    private final AppUserValidationService validationService;

    public AppUserValidationServiceTest(@Mock AppUserRepository repository, @Mock AppUserService service) {
        this.repository = repository;
        this.service = service;
        validationService = new AppUserValidationService(repository);
        validationService.setService(service);
    }

    @Test
    void testBindingResultValidation_successCase() {
        assertThatCode(() -> validationService.bindingResultValidation(emptyErrors())).doesNotThrowAnyException();
    }

    @Test
    void testBindingResultValidation_throwsCase() {
        assertThatThrownBy(() -> validationService.bindingResultValidation(notEmptyErrors())).isInstanceOf(FieldsValidationException.class);
    }

    @Test
    void testAddingValidation_successCase() {
        doReturn(false).when(repository).existsByUsername("test@mail");
        assertThatCode(() -> validationService.addingValidation(appUserAddingDto(), emptyErrors())).doesNotThrowAnyException();
    }

    @Test
    void testAddingValidation_throwsCase() {
        doReturn(true).when(repository).existsByUsername("test@mail");
        assertThatCode(() -> validationService.addingValidation(appUserAddingDto(), emptyErrors()))
                .isInstanceOf(FieldsValidationException.class)
                .extracting("fieldErrors", ITERABLE)
                .hasSize(1)
                .isEqualTo(List.of(new FieldError("existsByUsername", "username", "Такое имя пользователя уже существует")));
    }

    @Test
    void testEditingValidation_successWhenDoesNotExistsCase() {
        AppUser appUser = appUser(1);
        doReturn(appUser).when(service).findById(1);
        doReturn(false).when(repository).existsByUsername(any());
        assertThat(validationService.editingValidation(appUserEditingDto(1, "test@mail"), emptyErrors())).isEqualTo(appUser);
    }

    @Test
    void testEditingValidation_successWhenExistsButNotSameCase() {
        AppUser appUser = appUser(1);
        doReturn(appUser).when(service).findById(1);
        doReturn(true).when(repository).existsByUsername("test@mail");
        assertThat(validationService.editingValidation(appUserEditingDto(1, "otherTestMail@mail"), emptyErrors())).isEqualTo(appUser);
    }

    @Test
    void testEditingValidation_throwsWhenNotFoundCase() {
        doThrow(EntityNoFoundException.class).when(service).findById(1);
        assertThatThrownBy(() -> validationService.editingValidation(appUserEditingDto(1, "test@mail"), emptyErrors()))
                .isInstanceOf(EntityNoFoundException.class);
    }

    @Test
    void testEditingValidation_throwsWhenExistsAndNotSameCase() {
        AppUser appUser = appUser(1);
        appUser.setUsername("test@mail");
        doReturn(appUser).when(service).findById(1);
        doReturn(true).when(repository).existsByUsername("newTest@mail");
        assertThatThrownBy(() -> validationService.editingValidation(appUserEditingDto(1, "newTest@mail"), emptyErrors()))
                .isInstanceOf(FieldsValidationException.class)
                .extracting("fieldErrors", ITERABLE)
                .hasSize(1)
                .isEqualTo(List.of(new FieldError("existsByUsername", "username", "Такое имя пользователя уже существует")));
    }

    @Test
    void testDeletionValidation_successCase() {
        assertThatCode(() -> validationService.deletionValidation(new AppUser())).doesNotThrowAnyException();
    }

    @Test
    void testDeletionValidation_throwsWhenNotEmptyCreatedCommentsCase() {
        AppUser appUser = new AppUser();
        appUser.setCreatedComments(Set.of(new Comment()));
        assertThatThrownBy(() -> validationService.deletionValidation(appUser))
                .isInstanceOf(InputValidationException.class)
                .hasMessage("Этот пользователь не может быть удалён, так как в приложении есть его комментарии, товары или теги");
    }

    @Test
    void testDeletionValidation_throwsWhenNotEmptyCreatedProductsCase() {
        AppUser appUser = new AppUser();
        appUser.setCreatedProducts(Set.of(new Product()));
        assertThatThrownBy(() -> validationService.deletionValidation(appUser))
                .isInstanceOf(InputValidationException.class)
                .hasMessage("Этот пользователь не может быть удалён, так как в приложении есть его комментарии, товары или теги");
    }

    @Test
    void testDeletionValidation_throwsWhenNotEmptyCreatedTagsCase() {
        AppUser appUser = new AppUser();
        appUser.setCreatedTags(Set.of(new Tag()));
        assertThatThrownBy(() -> validationService.deletionValidation(appUser))
                .isInstanceOf(InputValidationException.class)
                .hasMessage("Этот пользователь не может быть удалён, так как в приложении есть его комментарии, товары или теги");
    }

}
