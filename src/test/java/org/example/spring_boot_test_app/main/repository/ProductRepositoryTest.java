package org.example.spring_boot_test_app.main.repository;

import org.example.spring_boot_test_app.main.entities.Product;
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
                "classpath:sql/tables/product.sql"
        },
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class ProductRepositoryTest {

    static Product product(String name, String description, Integer creationYear) {
        return Product
                .builder()
                .name(name == null ? "ProductName" : name)
                .description(description == null ? "ProductDescription" : description)
                .author(null)
                .creationDate(LocalDateTime.of(creationYear == null ? 2005 : creationYear, 2, 24, 12, 51, 3))
                .build();
    }

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    void testExistsByName_trueCase() {
        Product product = product(null, null, null);
        testEntityManager.persist(product);
        assertThat(repository.existsByName("ProductName")).isTrue();
    }

    @Test
    void testExistsByName_falseCase() {
        assertThat(repository.existsByName("ProductName")).isFalse();
    }

    @Test
    void testExistsByDescription_trueCase() {
        Product product = product(null, null, null);
        testEntityManager.persist(product);
        assertThat(repository.existsByDescription("ProductDescription")).isTrue();
    }

    @Test
    void testExistsByDescription_falseCase() {
        assertThat(repository.existsByDescription("ProductDescription")).isFalse();
    }

    @Test
    void testFindAllByOrderByCreationDateDesc_notEmptySuccessCase() {
        Product product1 = testEntityManager.persist(product("ProductName1", "ProductDescription1", 2018));
        Product product2 = testEntityManager.persist(product("ProductName2", "ProductDescription2", 2014));
        Product product3 = testEntityManager.persist(product("ProductName3", "ProductDescription3", 2010));
        Product product4 = testEntityManager.persist(product("ProductName4", "ProductDescription4", 2011));
        Product product5 = testEntityManager.persist(product("ProductName5", "ProductDescription5", 2015));
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(
                Stream.of(product1, product2, product3, product4, product5)
                        .sorted(Comparator.comparing(Product::getCreationDate).reversed())
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

    @Test
    void testFindAllByOrderByCreationDateDesc_emptySuccessCase() {
        assertThat(repository.findAllByOrderByCreationDateDesc()).isEqualTo(new LinkedHashSet<>());
    }

}
