package org.example.spring_boot_test_app.main.dto.in.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public abstract class ProductSavingDto {

    @NotEmpty(message = "Обязательное поле")
    @Size(min = 3, max = 255, message = "Допустимый диапазон длины строки: [3, 255]")
    private String name;

    @NotEmpty(message = "Обязательное поле")
    @Size(min = 8, max = 1024, message = "Допустимый диапазон длины строки: [8, 1024]")
    private String description;

    private Set<Integer> tags = new LinkedHashSet<>();

}
