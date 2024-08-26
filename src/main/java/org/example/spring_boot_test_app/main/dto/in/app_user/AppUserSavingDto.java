package org.example.spring_boot_test_app.main.dto.in.app_user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.spring_boot_test_app.main.entities.enums.Role;

@Data
public abstract class AppUserSavingDto {

    @NotEmpty(message = "Обязательное поле")
    @Size(min = 5, max = 255, message = "Допустимый диапазон длины строки: [5, 255]")
    private String username;

    @NotEmpty(message = "Обязательное поле")
    @Size(min = 3, max = 63, message = "Допустимый диапазон длины строки: [3, 63]")
    private String password;

    @NotNull(message = "Обязательное поле")
    private Role role;

}
