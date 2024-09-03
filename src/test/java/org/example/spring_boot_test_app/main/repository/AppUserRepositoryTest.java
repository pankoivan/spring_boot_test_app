package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AppUserRepositoryTest {

    static AppUser appUser(String username, Integer creationYear) {
        return AppUser
                .builder()
                .username(username == null ? "someMail@yandex" : username).password("789").role(Role.ADMIN)
                .creationDate(LocalDateTime.of(creationYear == null ? 2005 : creationYear, 2, 24, 12, 51, 3))
                .build();
    }

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AppUserRepository repository;

    // JPA methods

    @Test
    void testSave_successCase() {
        AppUser appUser = appUser(null, null);
        appUser = repository.save(appUser);
        assertThat(testEntityManager.find(AppUser.class, appUser.getId())).isEqualTo(appUser);
    }

    @Test
    void testFindById_foundSuccessCase() {
        AppUser appUser = appUser(null, null);
        appUser = testEntityManager.persist(appUser);
        assertThat(repository.findById(appUser.getId())).isPresent().get().isEqualTo(appUser);
    }

    @Test
    void testFindById_notFoundSuccessCase() {
        assertThat(repository.findById(142857)).isEmpty();
    }

    @Test
    void testUpdate_successCase() {
        AppUser appUser = appUser(null, null);
        appUser = testEntityManager.persist(appUser);
        appUser.setUsername("newUsername@mail");
        repository.save(appUser);
        assertThat(testEntityManager.find(AppUser.class, appUser.getId()).getUsername()).isEqualTo("newUsername@mail");
    }

    @Test
    void testDeleteById_successCase() {
        AppUser appUser = appUser(null, null);
        appUser = testEntityManager.persist(appUser);
        repository.deleteById(appUser.getId());
        assertThat(testEntityManager.find(AppUser.class, appUser.getId())).isNull();
    }

    // custom methods

    @Test
    void testFindByUsername_foundSuccessCase() {
        AppUser appUser = appUser(null, null);
        appUser = testEntityManager.persist(appUser);
        assertThat(repository.findByUsername("someMail@yandex")).isPresent().get().isEqualTo(appUser);
    }

    @Test
    void testFindByUsername_notFoundSuccessCase() {
        assertThat(repository.findByUsername("someMail@yandex")).isEmpty();
    }

    @Test
    void testExistsByUsername_trueCase() {
        AppUser appUser = appUser(null, null);
        testEntityManager.persist(appUser);
        assertThat(repository.existsByUsername("someMail@yandex")).isTrue();
    }

    @Test
    void testExistsByUsername_falseCase() {
        assertThat(repository.existsByUsername("someMail@yandex")).isFalse();
    }

    @Test
    void testFindAllByOrderByCreationDateDesc_notEmptySuccessCase() {
        AppUser appUser1 = testEntityManager.persist(appUser("mail1@mail", 2018));
        AppUser appUser2 = testEntityManager.persist(appUser("mail2@mail", 2014));
        AppUser appUser3 = testEntityManager.persist(appUser("mail3@mail", 2010));
        AppUser appUser4 = testEntityManager.persist(appUser("mail4@mail", 2011));
        AppUser appUser5 = testEntityManager.persist(appUser("mail5@mail", 2015));
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(
                Stream.of(appUser1, appUser2, appUser3, appUser4, appUser5)
                        .sorted(Comparator.comparing(AppUser::getCreationDate).reversed())
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

    @Test
    void testFindAllByOrderByCreationDateDesc_emptySuccessCase() {
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(new LinkedHashSet<>());
    }

}
