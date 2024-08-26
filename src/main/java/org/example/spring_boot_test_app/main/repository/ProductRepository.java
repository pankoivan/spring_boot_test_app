package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
