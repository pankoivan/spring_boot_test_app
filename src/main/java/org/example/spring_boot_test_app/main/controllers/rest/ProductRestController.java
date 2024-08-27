package org.example.spring_boot_test_app.main.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.spring_boot_test_app.auxiliary.UrlUtils;
import org.example.spring_boot_test_app.main.dto.in.product.ProductAddingDto;
import org.example.spring_boot_test_app.main.dto.in.product.ProductEditingDto;
import org.example.spring_boot_test_app.main.dto.out.ProductOutDto;
import org.example.spring_boot_test_app.main.filters.implementations.ProductSearchFilter;
import org.example.spring_boot_test_app.main.mappers.ProductOutDtoMapper;
import org.example.spring_boot_test_app.main.services.base.implementations.ProductService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
@AllArgsConstructor
public class ProductRestController {

    private final ProductService service;

    private final ProductSearchFilter filter;

    private final ProductOutDtoMapper mapper;

    @GetMapping("/{id}")
    public ProductOutDto findById(@PathVariable("id") String pathId) {
        return mapper.map(service.findById(UrlUtils.parsePathId(pathId)));
    }

    @GetMapping
    public List<ProductOutDto> findAll(@RequestParam("search") String search) {
        return mapper.map(filter.searched(service.findAll(), search));
    }

    @GetMapping("/add")
    public void add(@Valid ProductAddingDto addingDto, BindingResult bindingResult) {
        service.add(addingDto, bindingResult);
    }

    @GetMapping("/edit")
    public void edit(@Valid ProductEditingDto editingDto, BindingResult bindingResult) {
        service.edit(editingDto, bindingResult);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(UrlUtils.parsePathId(pathId));
    }

}
