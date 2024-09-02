package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.ldap.AutoConfigureDataLdap;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/*@DataJpaTest(properties = {
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.jpa.defer-datasource-initialization=true"
})*/
@DataJpaTest
//@TestPropertySource(locations = "classpath:folder/application.properties")
class AppUserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository repository;

    @Test
    void test() {
        AppUser appUser3 = AppUser
                .builder()
                .username("someMail@yandex").password("789").role(Role.ADMIN)
                .build();
        appUser3.setCreationDate(LocalDateTime.of(2005, 2, 24, 12, 51, 3));
        entityManager.persist(appUser3);
        entityManager.flush();
        System.out.println(entityManager);
        assertThat(repository.existsByUsername("someMail@yandex")).isTrue();
    }

    @Test
    void test2() {
        assertThat(repository.existsByUsername("someMail@yandex")).isFalse();
    }

}
