package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.auxiliary.SearchUtils;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.filters.interfaces.common.SearchFilter;
import org.springframework.stereotype.Component;

@Component
public class CommentSearchFilter implements SearchFilter<Comment> {

    @Override
    public boolean matches(Comment comment, String search) {
        return SearchUtils.biDirectionalContainsIgnoreCase(comment.getText(), search);
    }

}
