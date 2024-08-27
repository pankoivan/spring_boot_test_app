package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.auxiliary.SearchUtils;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.example.spring_boot_test_app.main.filters.interfaces.common.SearchFilter;
import org.springframework.stereotype.Component;

@Component
public class TagSearchFilter implements SearchFilter<Tag> {

    @Override
    public boolean matches(Tag tag, String search) {
        return SearchUtils.biDirectionalContainsIgnoreCase(tag.getName(), search);
    }

}
