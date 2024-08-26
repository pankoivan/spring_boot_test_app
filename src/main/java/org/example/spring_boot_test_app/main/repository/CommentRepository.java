package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
