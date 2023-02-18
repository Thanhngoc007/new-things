package fa.training.studentmanagement.repository.custom;

import fa.training.studentmanagement.dto.FaClassSummaryDto;

import java.util.List;

public interface CustomizedFaClassRepository {
    List<FaClassSummaryDto> getFaClassSummary();
}
