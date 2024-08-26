package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByName(String name);

    boolean existsByName(String name);

}
