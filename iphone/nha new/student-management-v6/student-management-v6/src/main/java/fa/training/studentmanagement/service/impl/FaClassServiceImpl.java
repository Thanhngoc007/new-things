package fa.training.studentmanagement.service.impl;

import fa.training.studentmanagement.dto.FaClassSummaryDto;
import fa.training.studentmanagement.entity.FaClass;
import fa.training.studentmanagement.enums.DeleteFlg;
import fa.training.studentmanagement.repository.FaClassRepository;
import fa.training.studentmanagement.service.FaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaClassServiceImpl
        extends BaseServiceImpl<FaClass, Long, FaClassRepository>
        implements FaClassService {
    @Override
    public List<FaClassSummaryDto> getFaClassSummary() {
        return repository.getFaClassSummary();
    }
}
