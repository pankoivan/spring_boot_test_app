package org.example.spring_boot_test_app.main.dto.out;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentOutDto {

    private String productName;

    private String authorUsername;

    private String text;

}
