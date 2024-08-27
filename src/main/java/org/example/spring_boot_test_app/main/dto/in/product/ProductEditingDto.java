package org.example.spring_boot_test_app.main.dto.in.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductEditingDto extends ProductSavingDto {

    @NotNull(message = "Обязательное поле")
    private Integer id;

    @NotNull(message = "Обязательное поле")
    private ProductEditingMode tagsEditing;

    public enum ProductEditingMode {

        NO_EDIT_TAGS,

        ADD_TAGS,

        REMOVE_TAGS

    }

}
