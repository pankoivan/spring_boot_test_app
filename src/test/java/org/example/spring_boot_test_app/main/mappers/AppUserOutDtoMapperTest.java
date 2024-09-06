package org.example.spring_boot_test_app.main.mappers;

import org.example.spring_boot_test_app.main.dto.out.AppUserOutDto;
import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AppUserOutDtoMapperTest {

    private final AppUser appUser1 = AppUser
            .builder()
            .username("testMail@mail").password("123").role(Role.ADMIN)
            .build();

    private final AppUserOutDto outDto1 = AppUserOutDto
            .builder()
            .username("testMail@mail").role(Role.ADMIN.getAlias())
            .build();

    private final AppUser appUser2 = AppUser
            .builder()
            .username("oneMoreTestMail@mail").password("456").role(Role.USER)
            .build();

    private final AppUserOutDto outDto2 = AppUserOutDto
            .builder()
            .username("oneMoreTestMail@mail").role(Role.USER.getAlias())
            .build();

    private final AppUser appUser3 = AppUser
            .builder()
            .username("someMail@yandex").password("789").role(Role.ADMIN)
            .build();

    private final AppUserOutDto outDto3 = AppUserOutDto
            .builder()
            .username("someMail@yandex").role(Role.ADMIN.getAlias())
            .build();

    @Spy
    private AppUserOutDtoMapper mapper;

    @Test
    void testMap_notNullSuccessCase() {
        assertThat(mapper.map(appUser1)).isEqualTo(outDto1);
    }

    @Test
    void testMap_nullSuccessCase() {
        assertThat(mapper.map((AppUser) null)).isNull();
    }

    @Test
    void testMapList_notEmptySuccessCase() {
        doReturn(outDto1).when(mapper).map(appUser1);
        doReturn(outDto2).when(mapper).map(appUser2);
        doReturn(outDto3).when(mapper).map(appUser3);
        assertThat(mapper.map(List.of(appUser1, appUser2, appUser3))).isEqualTo(List.of(outDto1, outDto2, outDto3));
    }

    @Test
    void testMapList_emptySuccessCase() {
        assertThat(mapper.map(List.of())).isEqualTo(List.of());
    }

    @Test
    void testMapList_nullSuccessCase() {
        assertThat(mapper.map((Collection<AppUser>) null)).isNull();
    }

}
