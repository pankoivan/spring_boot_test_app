package org.example.spring_boot_test_app.main.services.base.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.product.ProductAddingDto;
import org.example.spring_boot_test_app.main.dto.in.product.ProductEditingDto;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.example.spring_boot_test_app.main.exceptions.EntityNotFoundException;
import org.example.spring_boot_test_app.main.repository.ProductRepository;
import org.example.spring_boot_test_app.main.services.access.implementations.ProductAccessService;
import org.example.spring_boot_test_app.main.services.auxiliary.interfaces.CurrentUserService;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.example.spring_boot_test_app.main.services.validation.implementations.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class ProductService implements BaseService<Product, ProductAddingDto, ProductEditingDto> {

    private final ProductRepository repository;

    private ProductAccessService accessService;

    private ProductValidationService validationService;

    private CurrentUserService currentUserService;

    private TagService tagService;

    @Override
    public Product findById(Integer id) {
        Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Товар с id \"%s\" не найден"));
        accessService.shouldRead(product);
        return product;
    }

    @Override
    public Set<Product> findAll() {
        return accessService.availableOnly(repository.findAllByOrderByCreationDateDesc());
    }

    @Override
    public Product add(ProductAddingDto addingDto, BindingResult bindingResult) {
        validationService.addingValidation(addingDto, bindingResult);
        accessService.shouldCreate();
        return repository.save(
                Product
                        .builder()
                        .name(addingDto.getName())
                        .description(addingDto.getDescription())
                        .author(currentUserService.appUser())
                        .tags(mapTags(addingDto.getTags()))
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public Product edit(ProductEditingDto editingDto, BindingResult bindingResult) {
        Product product = validationService.editingValidation(editingDto, bindingResult);
        accessService.shouldEdit(product);
        switch (editingDto.getProductEditingMode()) {
            case NO_EDIT_TAGS -> {
                product.setName(editingDto.getName());
                product.setDescription(editingDto.getDescription());
            }
            case ADD_TAGS -> product.getTags().addAll(mapTags(editingDto.getTags()));
            case REMOVE_TAGS -> product.getTags().removeAll(mapTags(editingDto.getTags()));
        }
        product.setEditingDate(LocalDateTime.now());
        return repository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        Product product = findById(id);
        validationService.deletionValidation(product);
        accessService.shouldDelete(product);
        repository.deleteById(id);
    }

    private Set<Tag> mapTags(Set<Integer> tags) {
        return tags
                .stream()
                .map(tagService::findById)
                .collect(Collectors.toSet());
    }

}
