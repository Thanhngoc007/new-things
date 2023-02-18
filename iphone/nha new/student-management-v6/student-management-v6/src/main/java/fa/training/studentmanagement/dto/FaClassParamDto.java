package fa.training.studentmanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FaClassParamDto {

    private Long id;
    private String name;
    private String classCode;
    private String[] timeSlot; // MONDAY,TUESDAY
}
