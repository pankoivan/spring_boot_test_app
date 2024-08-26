package org.example.spring_boot_test_app.main.dto.in.tag;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class TagSavingDto {

    @NotEmpty(message = "Обязательное поле")
    @Size(min = 3, max = 255, message = "Допустимый диапазон длины строки: [3, 255]")
    private String name;

}
