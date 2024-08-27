package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.auxiliary.SearchUtils;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.filters.interfaces.common.SearchFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSearchFilter implements SearchFilter<Product> {

    @Override
    public boolean matches(Product product, String search) {
        return SearchUtils.biDirectionalContainsIgnoreCase(List.of(product.getName(), product.getDescription()), search);
    }

}
