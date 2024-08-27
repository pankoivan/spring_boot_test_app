package org.example.spring_boot_test_app.main.services.base.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.product.ProductAddingDto;
import org.example.spring_boot_test_app.main.dto.in.product.ProductEditingDto;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.exceptions.EntityNoFoundException;
import org.example.spring_boot_test_app.main.repository.ProductRepository;
import org.example.spring_boot_test_app.main.services.access.implementations.ProductAccessService;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.example.spring_boot_test_app.main.services.validation.implementations.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class ProductService implements BaseService<Product, ProductAddingDto, ProductEditingDto> {

    private final ProductRepository repository;

    private ProductAccessService accessService;

    private final ProductValidationService validationService;

    @Override
    public Product findById(Integer id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNoFoundException("Товар с id \"%s\" не найден"));
        accessService.shouldRead(product);
        return product;
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
