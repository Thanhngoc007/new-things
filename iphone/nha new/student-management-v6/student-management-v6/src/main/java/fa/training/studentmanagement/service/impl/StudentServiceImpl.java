package fa.training.studentmanagement.service.impl;

import fa.training.studentmanagement.entity.FaClass;
import fa.training.studentmanagement.entity.Student;
import fa.training.studentmanagement.enums.DeleteFlg;
import fa.training.studentmanagement.repository.FaClassRepository;
import fa.training.studentmanagement.repository.StudentRepository;
import fa.training.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Spring Bean
@Primary
@Service
public class StudentServiceImpl
        extends BaseServiceImpl<Student, Long, StudentRepository>
        implements StudentService {

    @Override
    public boolean existEmail(String email) {
        if (email == null) {
            return false;
        }
        return repository.existsByEmailAndDeleteFlg(email, DeleteFlg.UNDELETE);
    }

    @Override
    public List<Student> getEnrollStudentList() {
        return repository.getEnrollStudentList(DeleteFlg.UNDELETE.name());
    }

}
