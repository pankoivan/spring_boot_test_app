package org.example.spring_boot_test_app.main.services.validation.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.product.ProductAddingDto;
import org.example.spring_boot_test_app.main.dto.in.product.ProductEditingDto;
import org.example.spring_boot_test_app.main.dto.in.product.ProductSavingDto;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.exceptions.InputValidationException;
import org.example.spring_boot_test_app.main.repository.ProductRepository;
import org.example.spring_boot_test_app.main.services.base.implementations.ProductService;
import org.example.spring_boot_test_app.main.services.validation.interfaces.common.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class ProductValidationService implements ValidationService<Product, ProductSavingDto, ProductAddingDto, ProductEditingDto> {

    private final ProductRepository repository;

    private ProductService service;

    @Override
    public void savingValidation(ProductSavingDto savingDto, BindingResult bindingResult) {
        bindingResultValidation(bindingResult);
    }

    @Override
    public void addingValidation(ProductAddingDto addingDto, BindingResult bindingResult) {
        if (repository.existsByName(addingDto.getName())) {
            bindingResult.addError(new FieldError("existsByName", "name", "Такое название уже существует"));
        }
        if (repository.existsByDescription(addingDto.getDescription())) {
            bindingResult.addError(new FieldError("existsByDescription", "description", "Такое описание уже существует"));
        }
        savingValidation(addingDto, bindingResult);
    }

    @Override
    public Product editingValidation(ProductEditingDto editingDto, BindingResult bindingResult) {
        Product team = service.findById(editingDto.getId());
        if (repository.existsByName(editingDto.getName()) && !team.getName().equals(editingDto.getName())) {
            bindingResult.addError(new FieldError("existsByName", "name", "Такое название уже существует"));
        }
        if (repository.existsByDescription(editingDto.getDescription()) && !team.getDescription().equals(editingDto.getDescription())) {
            bindingResult.addError(new FieldError("existsByDescription", "description", "Такое описание уже существует"));
        }
        savingValidation(editingDto, bindingResult);
        return team;
    }

    @Override
    public void deletionValidation(Product product) {
        if (!product.getComments().isEmpty()) {
            throw new InputValidationException("Этот товар не может быть удалён, так как за ним закреплены комментарии");
        }
    }

}
