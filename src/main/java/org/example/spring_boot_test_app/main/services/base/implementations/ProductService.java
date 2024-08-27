package org.example.spring_boot_test_app.main.services.base.implementations;

import org.example.spring_boot_test_app.main.dto.in.product.ProductAddingDto;
import org.example.spring_boot_test_app.main.dto.in.product.ProductEditingDto;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.springframework.validation.BindingResult;

import java.util.Set;

public class ProductService implements BaseService<Product, ProductAddingDto, ProductEditingDto> {

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public Set<Product> findAll() {
        return Set.of();
    }

    @Override
    public Product add(ProductAddingDto addingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Product edit(ProductEditingDto editingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
