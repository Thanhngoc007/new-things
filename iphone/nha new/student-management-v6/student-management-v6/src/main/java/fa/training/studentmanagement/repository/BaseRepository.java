package fa.training.studentmanagement.repository;

import fa.training.studentmanagement.enums.DeleteFlg;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends
        CrudRepository<T, ID> {
    List<T> findAllByDeleteFlg(DeleteFlg deleteFlg);

    Optional<T> findByIdAndDeleteFlg(ID id, DeleteFlg deleteFlg);
}
