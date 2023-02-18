package fa.training.studentmanagement.repository;

import fa.training.studentmanagement.entity.Student;
import fa.training.studentmanagement.enums.DeleteFlg;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends
        BaseRepository<Student, Long> {
    boolean existsByEmailAndDeleteFlg(String email, DeleteFlg deleteFlg);

    @Query(value = "SELECT DISTINCT s.* FROM students s\n" +
                    "INNER JOIN fa_class_student cs ON s.id = cs.student_id\n" +
                    "WHERE s.delete_flg = :deleteFlg", nativeQuery = true)
    List<Student> getEnrollStudentList(String deleteFlg);
}
