package org.example.spring_boot_test_app.main.dto.in.app_user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppUserAddingDto extends AppUserSavingDto {

}
