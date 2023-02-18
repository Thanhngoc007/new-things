package fa.training.studentmanagement.dto;

import fa.training.studentmanagement.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentDisplayDto {

    private Long id;

    private String name;

    private String email;

    private Gender gender;

    private String dateOfBirth;

    private String classCode;
}
