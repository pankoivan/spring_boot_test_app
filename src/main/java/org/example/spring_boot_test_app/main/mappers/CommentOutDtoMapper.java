package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.CommentOutDto;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.mappers.common.BaseListMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentOutDtoMapper implements BaseListMapper<Comment, CommentOutDto> {

    @Override
    public CommentOutDto map(Comment comment) {
        return comment == null ? null : CommentOutDto
                .builder()
                .productName(comment.getProduct().getName())
                .authorUsername(comment.getAuthor().getUsername())
                .text(comment.getText())
                .build();
    }

}
