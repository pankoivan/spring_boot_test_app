package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    LinkedHashSet<Comment> findAllByOrderByCreationDateDesc();

}
