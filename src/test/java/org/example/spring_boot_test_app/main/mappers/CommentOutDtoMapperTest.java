package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.CommentOutDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.entities.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentOutDtoMapperTest {

    private final Comment comment = Comment
            .builder()
            .text("CommentText")
            .product(Product.builder().name("ProductName").build())
            .author(AppUser.builder().username("author@mail").build())
            .build();

    private final CommentOutDto outDto = CommentOutDto
            .builder()
            .productName("ProductName")
            .authorUsername("author@mail")
            .text("CommentText")
            .build();

    private final CommentOutDtoMapper mapper = new CommentOutDtoMapper();

    @Test
    void testMap_notNullSuccessCase() {
        assertThat(mapper.map(comment)).isEqualTo(outDto);
    }

    @Test
    void testMap_nullSuccessCase() {
        assertThat(mapper.map((Comment) null)).isEqualTo(null);
    }

}
