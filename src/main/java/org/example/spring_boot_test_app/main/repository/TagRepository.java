package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    boolean existsByName(String name);

}
