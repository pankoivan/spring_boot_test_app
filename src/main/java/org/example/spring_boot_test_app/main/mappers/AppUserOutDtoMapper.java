package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.AppUserOutDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.mappers.common.BaseListMapper;
import org.springframework.stereotype.Component;

@Component
public class AppUserOutDtoMapper implements BaseListMapper<AppUser, AppUserOutDto> {

    @Override
    public AppUserOutDto map(AppUser appUser) {
        return appUser == null ? null : AppUserOutDto
                .builder()
                .username(appUser.getUsername())
                .role(appUser.getRole().getAlias())
                .build();
    }

}
