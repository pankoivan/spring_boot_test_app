package org.example.spring_boot_test_app.main.dto.in.tag;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TagEditingDto extends TagSavingDto {

    @NotNull(message = "Обязательное поле")
    private Integer id;

}
