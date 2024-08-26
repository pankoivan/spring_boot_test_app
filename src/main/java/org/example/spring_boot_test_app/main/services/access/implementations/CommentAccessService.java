package org.example.spring_boot_test_app.main.services.access.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.services.access.interfaces.common.AccessService;
import org.example.spring_boot_test_app.main.services.auxiliary.interfaces.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class CommentAccessService implements AccessService<Comment> {

    private CurrentUserService currentUserService;

    @Override
    public boolean canRead(Comment comment) {
        return !currentUserService.isAnonymous();
    }

    @Override
    public boolean canCreate() {
        return !currentUserService.isAnonymous();
    }

    @Override
    public boolean canEdit(Comment comment) {
        return currentUserService.isAdmin() || currentUserService.appUser().equals(comment.getAuthor());
    }

    @Override
    public boolean canDelete(Comment comment) {
        return currentUserService.isAdmin() || currentUserService.appUser().equals(comment.getAuthor());
    }

}
