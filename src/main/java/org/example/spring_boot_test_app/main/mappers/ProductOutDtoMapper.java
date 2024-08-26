package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.ProductOutDto;
import org.example.spring_boot_test_app.main.entities.Product;
import org.example.spring_boot_test_app.main.mappers.common.BaseListMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductOutDtoMapper implements BaseListMapper<Product, ProductOutDto> {

    @Override
    public ProductOutDto map(Product product) {
        return product == null ? null : ProductOutDto
                .builder()
                .authorUsername(comment.getAuthor().getUsername())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

}
