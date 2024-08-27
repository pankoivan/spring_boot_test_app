package org.example.spring_boot_test_app.main.services.base.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.tag.TagAddingDto;
import org.example.spring_boot_test_app.main.dto.in.tag.TagEditingDto;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.example.spring_boot_test_app.main.exceptions.EntityNoFoundException;
import org.example.spring_boot_test_app.main.repository.TagRepository;
import org.example.spring_boot_test_app.main.services.access.implementations.TagAccessService;
import org.example.spring_boot_test_app.main.services.auxiliary.interfaces.CurrentUserService;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.example.spring_boot_test_app.main.services.validation.implementations.TagValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class TagService implements BaseService<Tag, TagAddingDto, TagEditingDto> {

    private final TagRepository repository;

    private TagAccessService accessService;

    private TagValidationService validationService;

    private CurrentUserService currentUserService;

    @Override
    public Tag findById(Integer id) {
        Tag tag = repository.findById(id).orElseThrow(() -> new EntityNoFoundException("Тег с id \"%s\" не найден"));
        accessService.shouldRead(tag);
        return tag;
    }

    @Override
    public Set<Tag> findAll() {
        return accessService.availableOnly(repository.findAllByOrderByCreationDateDesc());
    }

    @Override
    public Tag add(TagAddingDto addingDto, BindingResult bindingResult) {
        validationService.addingValidation(addingDto, bindingResult);
        accessService.shouldCreate();
        return repository.save(
                Tag
                        .builder()
                        .name(addingDto.getName())
                        .author(currentUserService.appUser())
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public Tag edit(TagEditingDto editingDto, BindingResult bindingResult) {
        Tag tag = validationService.editingValidation(editingDto, bindingResult);
        accessService.shouldEdit(tag);
        tag.setName(editingDto.getName());
        tag.setEditingDate(LocalDateTime.now());
        return repository.save(tag);
    }

    @Override
    public void deleteById(Integer id) {
        Tag tag = findById(id);
        validationService.deletionValidation(tag);
        accessService.shouldDelete(tag);
        repository.deleteById(id);
    }

}
