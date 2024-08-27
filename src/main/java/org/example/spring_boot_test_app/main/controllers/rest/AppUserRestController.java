package org.example.spring_boot_test_app.main.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.spring_boot_test_app.auxiliary.UrlUtils;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserAddingDto;
import org.example.spring_boot_test_app.main.dto.in.app_user.AppUserEditingDto;
import org.example.spring_boot_test_app.main.dto.out.AppUserOutDto;
import org.example.spring_boot_test_app.main.filters.implementations.AppUserSearchFilter;
import org.example.spring_boot_test_app.main.mappers.AppUserOutDtoMapper;
import org.example.spring_boot_test_app.main.services.base.implementations.AppUserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
@AllArgsConstructor
public class AppUserRestController {

    private final AppUserService service;

    private final AppUserSearchFilter filter;

    private final AppUserOutDtoMapper mapper;

    @GetMapping("/{id}")
    public AppUserOutDto findById(@PathVariable("id") String pathId) {
        return mapper.map(service.findById(UrlUtils.parsePathId(pathId)));
    }

    @GetMapping
    public List<AppUserOutDto> findAll(@RequestParam("search") String search) {
        return mapper.map(filter.searched(service.findAll(), search));
    }

    @GetMapping("/add")
    public void add(@Valid AppUserAddingDto addingDto, BindingResult bindingResult) {
        service.add(addingDto, bindingResult);
    }

    @GetMapping("/edit")
    public void edit(@Valid AppUserEditingDto editingDto, BindingResult bindingResult) {
        service.edit(editingDto, bindingResult);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") String pathId) {
        service.deleteById(UrlUtils.parsePathId(pathId));
    }

}
