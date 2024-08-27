package org.example.spring_boot_test_app.main.services.base.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentAddingDto;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentEditingDto;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class CommentService implements BaseService<Comment, CommentAddingDto, CommentEditingDto> {

    @Override
    public Comment findById(Integer id) {
        return null;
    }

    @Override
    public Set<Comment> findAll() {
        return Set.of();
    }

    @Override
    public Comment add(CommentAddingDto addingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public Comment edit(CommentEditingDto editingDto, BindingResult bindingResult) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

}
