package org.example.spring_boot_test_app.main.services.access.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.services.auxiliary.implementations.CurrentUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ProductAccessServiceTest {

    static AppUser appUser() {
        return AppUser.builder().id(1).build();
    }

    static Product product(AppUser author) {
        return Product
                .builder()
                .id(1)
                .name("ProductName")
                .description("ProductDescription")
                .author(author)
                .build();
    }

    @Mock
    private CurrentUserServiceImpl currentUserService;

    @InjectMocks
    private ProductAccessService accessService;

    @Test
    void testCanRead_trueCase() {
        doReturn(false).when(currentUserService).isAnonymous();
        assertThat(accessService.canRead(product(appUser()))).isTrue();
    }

    @Test
    void testCanRead_falseCase() {
        doReturn(true).when(currentUserService).isAnonymous();
        assertThat(accessService.canRead(product(appUser()))).isFalse();
    }

    @Test
    void testCanCreate_trueCase() {
        doReturn(true).when(currentUserService).isAdmin();
        assertThat(accessService.canCreate()).isTrue();
    }

    @Test
    void testCanCreate_falseCase() {
        doReturn(false).when(currentUserService).isAdmin();
        assertThat(accessService.canCreate()).isFalse();
    }

    @Test
    void testCanEdit_trueCase() {
        doReturn(true).when(currentUserService).isAdmin();
        assertThat(accessService.canEdit(product(appUser()))).isTrue();
    }

    @Test
    void testCanEdit_falseCase() {
        doReturn(false).when(currentUserService).isAdmin();
        assertThat(accessService.canEdit(product(appUser()))).isFalse();
    }

    @Test
    void testCanDelete_trueCase() {
        doReturn(true).when(currentUserService).isAdmin();
        assertThat(accessService.canDelete(product(appUser()))).isTrue();
    }

    @Test
    void testCanDelete_falseCase() {
        doReturn(false).when(currentUserService).isAdmin();
        assertThat(accessService.canDelete(product(appUser()))).isFalse();
    }

}
