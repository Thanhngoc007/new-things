package fa.training.studentmanagement.service;

import fa.training.studentmanagement.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student, Long> {

    boolean existEmail(String email);

    List<Student> getEnrollStudentList();
}
