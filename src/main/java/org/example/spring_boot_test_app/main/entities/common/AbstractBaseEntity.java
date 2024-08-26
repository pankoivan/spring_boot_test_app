package org.example.spring_boot_test_app.main.entities.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.spring_boot_test_app.auxiliary.DateTimeFormatConstants;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "creationDate", "editingDate"})
@SuperBuilder
public class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "editing_date")
    private LocalDateTime editingDate;

    public String getFormattedCreationDate() {
        return DateTimeFormatConstants.DAY_MONTH_YEAR_AT_HOUR_MINUTE_SECOND.format(creationDate);
    }

    public String getFormattedEditingDate() {
        return editingDate == null ? null : DateTimeFormatConstants.DAY_MONTH_YEAR_AT_HOUR_MINUTE_SECOND.format(editingDate);
    }

    @Override
    public boolean equals(Object o) {
        return (o == this) ||
                (o.getClass().equals(getClass()) &&
                        id != null &&
                        id.equals(((AbstractBaseEntity) o).getId())
                );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
