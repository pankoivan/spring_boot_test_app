package org.example.spring_boot_test_app.main.services.validation.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentAddingDto;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentEditingDto;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentSavingDto;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.repository.CommentRepository;
import org.example.spring_boot_test_app.main.services.base.implementations.CommentService;
import org.example.spring_boot_test_app.main.services.validation.interfaces.common.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class CommentValidationService implements ValidationService<Comment, CommentSavingDto, CommentAddingDto, CommentEditingDto> {

    private final CommentRepository repository;

    private CommentService service;

    @Override
    public void savingValidation(CommentSavingDto savingDto, BindingResult bindingResult) {
        bindingResultValidation(bindingResult);
    }

    @Override
    public void addingValidation(CommentAddingDto addingDto, BindingResult bindingResult) {
        savingValidation(addingDto, bindingResult);
    }

    @Override
    public Comment editingValidation(CommentEditingDto editingDto, BindingResult bindingResult) {
        Comment comment = service.findById(editingDto.getId());
        savingValidation(editingDto, bindingResult);
        return comment;
    }

    @Override
    public void deletionValidation(Comment comment) {
        // novalidate
    }

}
