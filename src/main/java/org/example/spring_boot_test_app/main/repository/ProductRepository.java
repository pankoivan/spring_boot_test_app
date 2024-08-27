package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);

    boolean existsByDescription(String description);

}
