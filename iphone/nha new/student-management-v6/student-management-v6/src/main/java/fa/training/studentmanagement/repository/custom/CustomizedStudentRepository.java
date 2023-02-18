package fa.training.studentmanagement.repository.custom;

import fa.training.studentmanagement.dto.FaClassStudentEnroll;
import fa.training.studentmanagement.dto.FaClassSummaryDto;
import fa.training.studentmanagement.enums.DeleteFlg;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomizedStudentRepository {

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<FaClassStudentEnroll> getFaClassStudentEnroll() {
//        String sqlStatement = "SELECT * from students s\n" +
//                "inner JOIN fa_class_student fs\n" +
//                "ON s.id = fs.student_id";
//
//        return entityManager.createNativeQuery(sqlStatement, "FaClass.FaClassStudentEnrollMapping")
//
//                .getResultList();
//
//    }
}
