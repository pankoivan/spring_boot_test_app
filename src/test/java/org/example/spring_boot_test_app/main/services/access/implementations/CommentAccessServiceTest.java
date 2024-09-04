package org.example.spring_boot_test_app.main.services.access.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.example.spring_boot_test_app.main.services.auxiliary.implementations.CurrentUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CommentAccessServiceTest {

    static AppUser appUser(int id) {
        return AppUser.builder().id(id).build();
    }

    static Comment comment(int id, AppUser author) {
        return Comment
                .builder()
                .id(id)
                .text("CommentText")
                .author(author)
                .build();
    }

    @Mock
    private CurrentUserServiceImpl currentUserService;

    @InjectMocks
    private CommentAccessService accessService;

    @Test
    void testCanRead_trueCase() {
        doReturn(false).when(currentUserService).isAnonymous();
        assertThat(accessService.canRead(comment(1, appUser(1)))).isTrue();
    }

    @Test
    void testCanRead_falseCase() {
        doReturn(true).when(currentUserService).isAnonymous();
        assertThat(accessService.canRead(comment(1, appUser(1)))).isFalse();
    }

    @Test
    void testCanCreate_trueCase() {
        doReturn(false).when(currentUserService).isAnonymous();
        assertThat(accessService.canCreate()).isTrue();
    }

    @Test
    void testCanCreate_falseCase() {
        doReturn(true).when(currentUserService).isAnonymous();
        assertThat(accessService.canCreate()).isFalse();
    }

    @Test
    void testCanEdit_trueWhenAdminCase() {
        doReturn(true).when(currentUserService).isAdmin();
        assertThat(accessService.canEdit(comment(1, appUser(1)))).isTrue();
    }

    @Test
    void testCanEdit_trueWhenAuthorCase() {
        AppUser appUser = appUser(1);
        doReturn(false).when(currentUserService).isAdmin();
        doReturn(appUser).when(currentUserService).appUser();
        assertThat(accessService.canEdit(comment(1, appUser))).isTrue();
    }

    @Test
    void testCanEdit_falseCase() {
        AppUser appUser = appUser(1);
        doReturn(false).when(currentUserService).isAdmin();
        doReturn(appUser).when(currentUserService).appUser();
        assertThat(accessService.canEdit(comment(1, appUser(2)))).isFalse();
    }

    @Test
    void testCanDelete_trueCase() {
        doReturn(true).when(currentUserService).isAdmin();
        assertThat(accessService.canDelete(comment(1, appUser(1)))).isTrue();
    }

    @Test
    void testCanDelete_falseWhenNotAdminCase() {
        AppUser appUser = appUser(1);
        doReturn(false).when(currentUserService).isAdmin();
        doReturn(appUser).when(currentUserService).appUser();
        assertThat(accessService.canDelete(comment(1, appUser))).isTrue();
    }

    @Test
    void testCanDelete_falseWhenSelfCase() {
        AppUser appUser = appUser(1);
        doReturn(false).when(currentUserService).isAdmin();
        doReturn(appUser).when(currentUserService).appUser();
        assertThat(accessService.canDelete(comment(1, appUser(2)))).isFalse();
    }

}
