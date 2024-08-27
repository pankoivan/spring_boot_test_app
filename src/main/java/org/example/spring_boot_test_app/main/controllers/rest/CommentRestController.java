package org.example.spring_boot_test_app.main.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.spring_boot_test_app.auxiliary.UrlUtils;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentAddingDto;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentEditingDto;
import org.example.spring_boot_test_app.main.dto.out.CommentOutDto;
import org.example.spring_boot_test_app.main.filters.implementations.CommentSearchFilter;
import org.example.spring_boot_test_app.main.mappers.CommentOutDtoMapper;
import org.example.spring_boot_test_app.main.services.base.implementations.CommentService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/comments")
@AllArgsConstructor
public class CommentRestController {

    private final CommentService service;

    private final CommentSearchFilter filter;

    private final CommentOutDtoMapper mapper;

    @GetMapping("/{id}")
    public CommentOutDto findById(@PathVariable("id") String pathId) {
        return mapper.map(service.findById(UrlUtils.parsePathId(pathId)));
    }

    @GetMapping
    public List<CommentOutDto> findAll(@RequestParam("search") String search) {
        return mapper.map(filter.searched(service.findAll(), search));
    }

    @GetMapping("/add")
    public void add(@Valid CommentAddingDto addingDto, BindingResult bindingResult) {
        service.add(addingDto, bindingResult);
    }

    @GetMapping("/edit")
    public void edit(@Valid CommentEditingDto editingDto, BindingResult bindingResult) {
        service.edit(editingDto, bindingResult);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(UrlUtils.parsePathId(pathId));
    }

}
