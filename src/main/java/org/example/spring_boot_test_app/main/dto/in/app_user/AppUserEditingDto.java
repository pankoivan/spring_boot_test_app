package org.example.spring_boot_test_app.main.dto.in.app_user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppUserEditingDto extends AppUserSavingDto {

    @NotNull(message = "Обязательное поле")
    private Integer id;

}
