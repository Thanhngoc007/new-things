package fa.training.studentmanagement.service;

import fa.training.studentmanagement.dto.FaClassSummaryDto;
import fa.training.studentmanagement.entity.FaClass;

import java.util.List;
import java.util.Optional;

public interface FaClassService extends BaseService<FaClass, Long> {
    List<FaClassSummaryDto> getFaClassSummary();
}
