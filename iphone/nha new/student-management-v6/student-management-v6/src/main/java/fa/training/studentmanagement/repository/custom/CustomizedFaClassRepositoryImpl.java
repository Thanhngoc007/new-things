package fa.training.studentmanagement.repository.custom;

import fa.training.studentmanagement.dto.FaClassSummaryDto;
import fa.training.studentmanagement.enums.DeleteFlg;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

public class CustomizedFaClassRepositoryImpl
        implements CustomizedFaClassRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FaClassSummaryDto> getFaClassSummary() {
//        String sqlStatement = "SELECT c.id, c.name, c.class_code, c.time_slot, COUNT(cs.student_id) AS total_student\n" +
//                "FROM fa_classes c\n" +
//                "LEFT JOIN fa_class_student cs ON c.id = cs.fa_class_id\n" +
//                "WHERE c.delete_flg = :deleteFlg\n" +
//                "GROUP BY c.id, c.class_code, c.name, c.time_slot";

        String sqlStatement = "SELECT c.id, c.name, c.class_code, c.time_slot, COUNT(s.id) AS total_student\n" +
                "                FROM fa_classes c\n" +
                "                LEFT JOIN fa_class_student cs ON c.id = cs.fa_class_id\n" +
                "                LEFT JOIN students s ON s.id = cs.student_id AND s.delete_flg = :deleteFlg\n" +
                "                WHERE c.delete_flg = :deleteFlg\n" +
                "                GROUP BY c.id, c.class_code, c.name, c.time_slot";

        return entityManager.createNativeQuery(sqlStatement, "FaClass.FaClassSummaryMapping")
                .setParameter("deleteFlg", DeleteFlg.UNDELETE.name())
                .getResultList();

    }
}
