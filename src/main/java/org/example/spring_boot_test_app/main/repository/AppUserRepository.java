package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);

}
