package fa.training.studentmanagement.entity;


import fa.training.studentmanagement.dto.FaClassSummaryDto;
import fa.training.studentmanagement.enums.DeleteFlg;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Set;

@SqlResultSetMapping(
        name = "FaClass.FaClassSummaryMapping",
        classes = @ConstructorResult(
                targetClass = FaClassSummaryDto.class,
                columns = {
                    @ColumnResult(name = "id", type = Long.class),
                    @ColumnResult(name = "name"),
                    @ColumnResult(name = "class_code"),
                    @ColumnResult(name = "time_slot"),
                    @ColumnResult(name = "total_student", type = Integer.class),
                }
        )
)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fa_classes")
public class FaClass extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized // => nvarchar
    private String name;

    @Column(name = "class_code")
    private String classCode;

    private String timeSlot; // MONDAY,TUESDAY

    @OneToMany(mappedBy = "faClass")
    private Set<FaClassStudent> faClassStudents;
}
