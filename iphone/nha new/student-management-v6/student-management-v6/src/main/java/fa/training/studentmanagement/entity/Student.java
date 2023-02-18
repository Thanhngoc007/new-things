package fa.training.studentmanagement.entity;

import fa.training.studentmanagement.dto.StudentDisplayDto;
import fa.training.studentmanagement.enums.DeleteFlg;
import fa.training.studentmanagement.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "students")
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized // => nvarchar
    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "class_code")
    private String classCode;

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<FaClassStudent> faClassStudents;

    public void setFaClassStudents(Set<FaClassStudent> faClassStudents) {
//        this.faClassStudents = faClassStudents;
        if (this.faClassStudents == null) {
            this.faClassStudents = faClassStudents;
        } else {
            this.faClassStudents.clear();
            this.faClassStudents.addAll(faClassStudents);
        }
    }

    public StudentDisplayDto convertToDisplayDto() {
        StudentDisplayDto studentDisplayDto = new StudentDisplayDto();
        BeanUtils.copyProperties(this, studentDisplayDto);
        if (this.getDateOfBirth() != null) {
            studentDisplayDto.setDateOfBirth(this.getDateOfBirth().toString());
        }
        if (this.getFaClassStudents() != null) { // FIXME: Because FaClassStudent has only ID
            String classCode = this.getFaClassStudents().stream()
                    .map(faClassStudent -> faClassStudent.getFaClass().getClassCode())
                    .collect(Collectors.joining(", "));
            studentDisplayDto.setClassCode(classCode);
        }
        return studentDisplayDto;
    }

}
