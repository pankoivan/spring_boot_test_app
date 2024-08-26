package org.example.spring_boot_test_app.main.dto.in.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TagAddingDto extends TagSavingDto {

}
