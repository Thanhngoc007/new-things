package fa.training.studentmanagement.dto;

import fa.training.studentmanagement.entity.FaClass;
import fa.training.studentmanagement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaClassStudentEnroll {
    private Long id;
    private Student student;
    private FaClass faClass;
    private LocalDateTime enrollDate;
    private String classCode;

    public FaClassStudentEnroll(Student student, FaClass build, LocalDateTime now, FaClass.FaClassBuilder classCode) {
        this.student = student;
        this.faClass = faClass;
        this.enrollDate = enrollDate;
        this.classCode = String.valueOf(classCode);
    }
}
