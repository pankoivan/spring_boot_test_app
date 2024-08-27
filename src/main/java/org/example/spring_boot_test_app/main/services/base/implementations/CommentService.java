package org.example.spring_boot_test_app.main.services.base.implementations;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentAddingDto;
import org.example.spring_boot_test_app.main.dto.in.comment.CommentEditingDto;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.exceptions.EntityNoFoundException;
import org.example.spring_boot_test_app.main.repository.CommentRepository;
import org.example.spring_boot_test_app.main.services.access.implementations.CommentAccessService;
import org.example.spring_boot_test_app.main.services.auxiliary.interfaces.CurrentUserService;
import org.example.spring_boot_test_app.main.services.base.interfaces.common.BaseService;
import org.example.spring_boot_test_app.main.services.validation.implementations.CommentValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Setter(onMethod_ = @Autowired)
public class CommentService implements BaseService<Comment, CommentAddingDto, CommentEditingDto> {

    private final CommentRepository repository;

    private CommentAccessService accessService;

    private CommentValidationService validationService;

    private ProductService productService;

    private CurrentUserService currentUserService;

    @Override
    public Comment findById(Integer id) {
        Comment comment = repository.findById(id).orElseThrow(() -> new EntityNoFoundException("Комментарий с id \"%s\" не найден"));
        accessService.shouldRead(comment);
        return comment;
    }

    @Override
    public Set<Comment> findAll() {
        return accessService.availableOnly(repository.findAllByOrderByCreationDateDesc());
    }

    @Override
    public Comment add(CommentAddingDto addingDto, BindingResult bindingResult) {
        validationService.addingValidation(addingDto, bindingResult);
        accessService.shouldCreate();
        return repository.save(
                Comment
                        .builder()
                        .text(addingDto.getText())
                        .product(productService.findById(addingDto.getProductId()))
                        .author(currentUserService.appUser())
                        .creationDate(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public Comment edit(CommentEditingDto editingDto, BindingResult bindingResult) {
        Comment comment = validationService.editingValidation(editingDto, bindingResult);
        accessService.shouldEdit(comment);
        comment.setText(editingDto.getText());
        comment.setEditingDate(LocalDateTime.now());
        return repository.save(comment);
    }

    @Override
    public void deleteById(Integer id) {
        Comment comment = findById(id);
        validationService.deletionValidation(comment);
        accessService.shouldDelete(comment);
        repository.deleteById(id);
    }

}
