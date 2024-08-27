package org.example.spring_boot_test_app.main.services.validation.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.tag.TagAddingDto;
import org.example.spring_boot_test_app.main.dto.in.tag.TagEditingDto;
import org.example.spring_boot_test_app.main.dto.in.tag.TagSavingDto;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.example.spring_boot_test_app.main.exceptions.InputValidationException;
import org.example.spring_boot_test_app.main.repository.TagRepository;
import org.example.spring_boot_test_app.main.services.validation.interfaces.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class TagValidationService implements ValidationService<Tag, TagSavingDto, TagAddingDto, TagEditingDto> {

    private final TagRepository repository;

    private TagService service;

    @Override
    public void savingValidation(TagSavingDto savingDto, BindingResult bindingResult) {
        bindingResultValidation(bindingResult);
    }

    @Override
    public void addingValidation(TagAddingDto addingDto, BindingResult bindingResult) {
        if (repository.existsByName(addingDto.getName())) {
            bindingResult.addError(new FieldError("existsByName", "name", "Такое название уже существует"));
        }
        savingValidation(addingDto, bindingResult);
    }

    @Override
    public Tag editingValidation(TagEditingDto editingDto, BindingResult bindingResult) {
        Tag tag = service.findById(editingDto.getId());
        if (repository.existsByName(editingDto.getName()) && !tag.getName().equals(editingDto.getName())) {
            bindingResult.addError(new FieldError("existsByName", "name", "Такое название уже существует"));
        }
        savingValidation(editingDto, bindingResult);
        return tag;
    }

    @Override
    public void deletionValidation(Tag tag) {
        if (!tag.getProducts().isEmpty()) {
            throw new InputValidationException("Этот тег не может быть удалён, так как за ним закреплены товары");
        }
    }

}
