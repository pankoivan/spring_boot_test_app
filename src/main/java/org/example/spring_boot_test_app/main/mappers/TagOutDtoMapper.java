package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.TagOutDto;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.example.spring_boot_test_app.main.mappers.common.BaseListMapper;
import org.springframework.stereotype.Component;

@Component
public class TagOutDtoMapper implements BaseListMapper<Tag, TagOutDto> {

    @Override
    public TagOutDto map(Tag tag) {
        return tag == null ? null : TagOutDto
                .builder()
                .authorUsername(tag.getAuthor().getUsername())
                .name(tag.getName())
                .build();
    }

}
