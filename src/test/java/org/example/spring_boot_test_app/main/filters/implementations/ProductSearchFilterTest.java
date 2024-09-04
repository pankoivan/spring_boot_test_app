package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ProductSearchFilterTest {

    private final Product product = Product
            .builder()
            .name("ProductName")
            .description("ProductDescription")
            .author(AppUser.builder().username("author@mail").build())
            .build();

    private final ProductSearchFilter filter = new ProductSearchFilter();

    @Test
    void testMatches_nameTrueCase() {
        assertThat(filter.matches(product, "name")).isTrue();
    }

    @Test
    void testMatches_descriptionTrueCase() {
        assertThat(filter.matches(product, "description")).isTrue();
    }

    @Test
    void testMatches_falseCase() {
        assertThat(filter.matches(product, "test")).isFalse();
    }

    @Test
    void testMatches_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> filter.matches(null, "AnyString"));
    }

}
