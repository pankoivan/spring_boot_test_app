package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.TagOutDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TagOutDtoMapperTest {

    private final Tag tag = Tag
            .builder()
            .name("TagName")
            .author(AppUser.builder().username("author@mail").build())
            .build();

    private final TagOutDto outDto = TagOutDto
            .builder()
            .authorUsername("author@mail")
            .name("TagName")
            .build();

    private final TagOutDtoMapper mapper = new TagOutDtoMapper();

    @Test
    void testMap_notNullSuccessCase() {
        assertThat(mapper.map(tag)).isEqualTo(outDto);
    }

    @Test
    void testMap_nullSuccessCase() {
        assertThat(mapper.map((Tag) null)).isNull();
    }

}
