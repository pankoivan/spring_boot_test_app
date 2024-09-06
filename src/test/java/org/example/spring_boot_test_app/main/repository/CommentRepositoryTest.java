package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Comment;
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
                "classpath:sql/tables/product.sql",
                "classpath:sql/tables/comment.sql"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class CommentRepositoryTest {

    static Comment comment(int creationYear) {
        return Comment
                .builder()
                .text("CommentText")
                .creationDate(LocalDateTime.of(creationYear, 2, 24, 12, 51, 3))
                .build();
    }

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CommentRepository repository;

    @Test
    void testFindAllByOrderByCreationDateDesc_notEmptySuccessCase() {
        Comment comment1 = testEntityManager.persist(comment(2018));
        Comment comment2 = testEntityManager.persist(comment(2014));
        Comment comment3 = testEntityManager.persist(comment(2010));
        Comment comment4 = testEntityManager.persist(comment(2011));
        Comment comment5 = testEntityManager.persist(comment(2015));
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(
                Stream.of(comment1, comment2, comment3, comment4, comment5)
                        .sorted(Comparator.comparing(Comment::getCreationDate).reversed())
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

    @Test
    void testFindAllByOrderByCreationDateDesc_emptySuccessCase() {
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(new LinkedHashSet<>());
    }

}
