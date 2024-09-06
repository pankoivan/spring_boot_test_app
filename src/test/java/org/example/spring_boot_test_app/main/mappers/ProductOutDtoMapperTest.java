package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.ProductOutDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductOutDtoMapperTest {

    private final Product product = Product
            .builder()
            .name("ProductName")
            .description("ProductDescription")
            .author(AppUser.builder().username("author@mail").build())
            .build();

    private final ProductOutDto outDto = ProductOutDto
            .builder()
            .authorUsername("author@mail")
            .name("ProductName")
            .description("ProductDescription")
            .build();

    private final ProductOutDtoMapper mapper = new ProductOutDtoMapper();

    @Test
    void testMap_notNullSuccessCase() {
        assertThat(mapper.map(product)).isEqualTo(outDto);
    }

    @Test
    void testMap_nullSuccessCase() {
        assertThat(mapper.map((Product) null)).isNull();
    }

}
