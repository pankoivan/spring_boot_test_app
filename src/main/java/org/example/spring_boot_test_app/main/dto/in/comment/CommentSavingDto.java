package org.example.spring_boot_test_app.main.dto.in.comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class CommentSavingDto {

    @NotEmpty(message = "Обязательное поле")
    @Size(min = 8, max = 1024, message = "Допустимый диапазон длины строки: [8, 1024]")
    private String text;

}
