package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(
        scripts = {
                "classpath:sql/tables/app_user.sql",
                "classpath:sql/tables/tag.sql"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class TagRepositoryTest {

    static Tag tag(String name, Integer creationYear) {
        return Tag
                .builder()
                .name(name)
                .creationDate(LocalDateTime.of(creationYear, 2, 24, 12, 51, 3))
                .build();
    }

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TagRepository repository;

    @Test
    void testExistsByName_trueCase() {
        testEntityManager.persist(tag("TagName", 2005));
        assertThat(repository.existsByName("TagName")).isTrue();
    }

    @Test
    void testExistsByName_falseCase() {
        assertThat(repository.existsByName("TagName")).isFalse();
    }

    @Test
    void testFindAllByOrderByCreationDateDesc_notEmptySuccessCase() {
        Tag tag1 = testEntityManager.persist(tag("TagName1", 2018));
        Tag tag2 = testEntityManager.persist(tag("TagName2", 2014));
        Tag tag3 = testEntityManager.persist(tag("TagName3", 2010));
        Tag tag4 = testEntityManager.persist(tag("TagName4", 2011));
        Tag tag5 = testEntityManager.persist(tag("TagName5", 2015));
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(
                Stream.of(tag1, tag2, tag3, tag4, tag5)
                        .sorted(Comparator.comparing(Tag::getCreationDate).reversed())
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

    @Test
    void testFindAllByOrderByCreationDateDesc_emptySuccessCase() {
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(new LinkedHashSet<>());
    }

}
