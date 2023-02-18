package fa.training.studentmanagement.dto;

import fa.training.studentmanagement.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentParamDto {

    private Long id;

    @NotBlank(message = "{student.name.notBlank}")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "{student.email.notBlank}")
    @Email(message = "{student.email.invalid.format}")
    private String email;

    private String password;

    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Long[] classIdList;
}
