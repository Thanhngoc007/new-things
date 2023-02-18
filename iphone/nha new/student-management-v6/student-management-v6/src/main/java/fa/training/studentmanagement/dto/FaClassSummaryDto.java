package fa.training.studentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaClassSummaryDto {

    private Long id;
    private String name;
    private String classCode;
    private String timeSlot; // MONDAY,TUESDAY
    private Integer totalStudent; // MONDAY,TUESDAY
}
