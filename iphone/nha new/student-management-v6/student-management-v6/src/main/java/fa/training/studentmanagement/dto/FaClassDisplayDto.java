package fa.training.studentmanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FaClassDisplayDto {

    private Long id;
    private String name;
    private String classCode;
    private String timeSlot; // MONDAY,TUESDAY
}
