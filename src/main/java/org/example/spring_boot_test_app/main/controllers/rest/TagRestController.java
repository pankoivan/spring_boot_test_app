package org.example.spring_boot_test_app.main.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.spring_boot_test_app.auxiliary.UrlUtils;
import org.example.spring_boot_test_app.main.dto.in.tag.TagAddingDto;
import org.example.spring_boot_test_app.main.dto.in.tag.TagEditingDto;
import org.example.spring_boot_test_app.main.dto.out.TagOutDto;
import org.example.spring_boot_test_app.main.filters.implementations.TagSearchFilter;
import org.example.spring_boot_test_app.main.mappers.TagOutDtoMapper;
import org.example.spring_boot_test_app.main.services.base.implementations.TagService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/tags")
@AllArgsConstructor
public class TagRestController {

    private final TagService service;

    private final TagSearchFilter filter;

    private final TagOutDtoMapper mapper;

    @GetMapping("/{id}")
    public TagOutDto findById(@PathVariable("id") String pathId) {
        return mapper.map(service.findById(UrlUtils.parsePathId(pathId)));
    }

    @GetMapping
    public List<TagOutDto> findAll(@RequestParam("search") String search) {
        return mapper.map(filter.searched(service.findAll(), search));
    }

    @GetMapping("/add")
    public void add(@Valid TagAddingDto addingDto, BindingResult bindingResult) {
        service.add(addingDto, bindingResult);
    }

    @GetMapping("/edit")
    public void edit(@Valid TagEditingDto editingDto, BindingResult bindingResult) {
        service.edit(editingDto, bindingResult);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(UrlUtils.parsePathId(pathId));
    }

}
