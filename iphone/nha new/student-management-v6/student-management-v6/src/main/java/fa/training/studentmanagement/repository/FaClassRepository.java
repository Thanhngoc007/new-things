package fa.training.studentmanagement.repository;

import fa.training.studentmanagement.entity.FaClass;
import fa.training.studentmanagement.enums.DeleteFlg;
import fa.training.studentmanagement.repository.custom.CustomizedFaClassRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaClassRepository extends
        BaseRepository<FaClass, Long>, CustomizedFaClassRepository {

}
