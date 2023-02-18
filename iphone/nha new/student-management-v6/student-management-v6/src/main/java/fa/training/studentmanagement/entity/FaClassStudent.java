package fa.training.studentmanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "fa_class_student")
public class FaClassStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private FaClass faClass;

    private LocalDateTime enrollDate;

    public FaClassStudent(Student student, FaClass faClass, LocalDateTime enrollDate) {
        this.student = student;
        this.faClass = faClass;
        this.enrollDate = enrollDate;
    }
}
