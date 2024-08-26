package org.example.spring_boot_test_app.main.services.access.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.services.access.interfaces.common.AccessService;
import org.example.spring_boot_test_app.main.services.auxiliary.interfaces.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class ProductAccessService implements AccessService<Product> {

    private CurrentUserService currentUserService;

    @Override
    public boolean canRead(Product product) {
        return !currentUserService.isAnonymous();
    }

    @Override
    public boolean canCreate() {
        return currentUserService.isAdmin();
    }

    @Override
    public boolean canEdit(Product product) {
        return currentUserService.isAdmin();
    }

    @Override
    public boolean canDelete(Product product) {
        return currentUserService.isAdmin();
    }

}
