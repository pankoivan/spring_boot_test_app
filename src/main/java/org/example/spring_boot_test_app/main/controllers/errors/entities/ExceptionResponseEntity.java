package org.example.spring_boot_test_app.main.controllers.errors.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponseEntity {

    private Integer errorStatusCode;

    private boolean fieldErrors;

}
