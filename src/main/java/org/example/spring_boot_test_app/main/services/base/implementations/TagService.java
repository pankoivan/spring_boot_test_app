package org.example.spring_boot_test_app.main.services.base.implementations;

import org.example.spring_boot_test_app.main.dto.in.tag.TagAddingDto;
import org.example.spring_boot_test_app.main.dto.in.tag.TagEditingDto;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.springframework.validation.BindingResult;

import java.util.Set;

public class TagService implements BaseService<Tag, TagAddingDto, TagEditingDto> {

    @Override
    public Tag findById(Integer id) {
        return null;
    }

    @Override
    public Set<Tag> findAll() {
        return Set.of();
    }

    @Override
    public Tag add(TagAddingDto addingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Tag edit(TagEditingDto editingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
