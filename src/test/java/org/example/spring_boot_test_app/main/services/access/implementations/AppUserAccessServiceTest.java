package org.example.spring_boot_test_app.main.services.access.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.example.spring_boot_test_app.main.exceptions.AccessRightsException;
import org.example.spring_boot_test_app.main.services.auxiliary.implementations.CurrentUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AppUserAccessServiceTest {

    static AppUser appUser(int id) {
        return AppUser
                .builder()
                .id(id)
                .username("testMail@mail")
                .password("123")
                .role(Role.USER)
                .build();
    }

    @Mock
    private CurrentUserServiceImpl currentUserService;

    @Spy
    @InjectMocks
    private AppUserAccessService accessService;

    // common methods

    @Test
    void testAvailableOnly_availableSuccessCase() {
        doReturn(true).when(accessService).canRead(any());
        assertThat(accessService.availableOnly(Set.of(appUser(1), appUser(2), appUser(3)))).isEqualTo(
                Stream.of(appUser(1), appUser(2), appUser(3))
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

    @Test
    void testAvailableOnly_notAvailableSuccessCase() {
        doReturn(false).when(accessService).canRead(any());
        assertThat(accessService.availableOnly(Set.of(appUser(1), appUser(2), appUser(3)))).isEqualTo(new LinkedHashSet<>());
    }

    @Test
    void testAvailableOnly_emptyAvailableSuccessCase() {
        assertThat(accessService.availableOnly(Set.of())).isEqualTo(new LinkedHashSet<>());
    }

    @Test
    void testAvailableOnly_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> accessService.availableOnly(null));
    }

    @Test
    void testShouldRead_successCase() {
        doReturn(true).when(accessService).canRead(appUser(1));
        assertDoesNotThrow(() -> accessService.shouldRead(appUser(1)));
    }

    @Test
    void testShouldRead_throwsCase() {
        doReturn(false).when(accessService).canRead(appUser(1));
        assertThatThrownBy(() -> accessService.shouldRead(appUser(1)))
                .isInstanceOf(AccessRightsException.class)
                .hasMessage("Недостаточно прав для чтения");
    }

    @Test
    void testShouldCreate_successCase() {
        doReturn(true).when(accessService).canCreate();
        assertDoesNotThrow(() -> accessService.shouldCreate());
    }

    @Test
    void testShouldCreate_throwsCase() {
        doReturn(false).when(accessService).canCreate();
        assertThatThrownBy(() -> accessService.shouldCreate())
                .isInstanceOf(AccessRightsException.class)
                .hasMessage("Недостаточно прав для создания");
    }

    @Test
    void testShouldEdit_successCase() {
        doReturn(true).when(accessService).canEdit(appUser(1));
        assertDoesNotThrow(() -> accessService.shouldEdit(appUser(1)));
    }

    @Test
    void testShouldEdit_throwsCase() {
        doReturn(false).when(accessService).canEdit(appUser(1));
        assertThatThrownBy(() -> accessService.shouldEdit(appUser(1)))
                .isInstanceOf(AccessRightsException.class)
                .hasMessage("Недостаточно прав для редактирования");
    }

    @Test
    void testShouldDelete_successCase() {
        doReturn(true).when(accessService).canDelete(appUser(1));
        assertDoesNotThrow(() -> accessService.shouldDelete(appUser(1)));
    }

    @Test
    void testShouldDelete_throwsCase() {
        doReturn(false).when(accessService).canDelete(appUser(1));
        assertThatThrownBy(() -> accessService.shouldDelete(appUser(1)))
                .isInstanceOf(AccessRightsException.class)
                .hasMessage("Недостаточно прав для удаления");
    }

    // specific methods

    @Test
    void testCanRead_trueCase() {
        doReturn(false).when(currentUserService).isAnonymous();
        assertThat(accessService.canRead(appUser(1))).isTrue();
    }

    @Test
    void testCanRead_falseCase() {
        doReturn(true).when(currentUserService).isAnonymous();
        assertThat(accessService.canRead(appUser(1))).isFalse();
    }

    @Test
    void testCanCreate_trueCase() {
        doReturn(true).when(currentUserService).isAnonymous();
        assertThat(accessService.canCreate()).isTrue();
    }

    @Test
    void testCanCreate_falseCase() {
        doReturn(false).when(currentUserService).isAnonymous();
        assertThat(accessService.canCreate()).isFalse();
    }

    @Test
    void testCanEdit_trueWhenAdminCase() {
        AppUser appUser = appUser(1);
        doReturn(true).when(currentUserService).isAdmin();
        assertThat(accessService.canEdit(appUser)).isTrue();
    }

    @Test
    void testCanEdit_trueWhenAuthorCase() {
        AppUser appUser = appUser(1);
        doReturn(false).when(currentUserService).isAdmin();
        doReturn(appUser).when(currentUserService).appUser();
        assertThat(accessService.canEdit(appUser)).isTrue();
    }

    @Test
    void testCanEdit_falseCase() {
        AppUser appUser = appUser(1);
        doReturn(false).when(currentUserService).isAdmin();
        doReturn(appUser).when(currentUserService).appUser();
        assertThat(accessService.canEdit(appUser(2))).isFalse();
    }

    @Test
    void testCanDelete_trueCase() {
        AppUser appUser = appUser(1);
        doReturn(true).when(currentUserService).isAdmin();
        doReturn(appUser(2)).when(currentUserService).appUser();
        assertThat(accessService.canDelete(appUser)).isTrue();
    }

    @Test
    void testCanDelete_falseWhenNotAdminCase() {
        doReturn(false).when(currentUserService).isAdmin();
        assertThat(accessService.canDelete(appUser(1))).isFalse();
    }

    @Test
    void testCanDelete_falseWhenSelfCase() {
        AppUser appUser = appUser(1);
        doReturn(true).when(currentUserService).isAdmin();
        doReturn(appUser).when(currentUserService).appUser();
        assertThat(accessService.canDelete(appUser)).isFalse();
    }

}
