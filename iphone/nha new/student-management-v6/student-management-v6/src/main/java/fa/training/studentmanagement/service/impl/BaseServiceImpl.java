package fa.training.studentmanagement.service.impl;

import fa.training.studentmanagement.entity.BaseEntity;
import fa.training.studentmanagement.enums.DeleteFlg;
import fa.training.studentmanagement.repository.BaseRepository;
import fa.training.studentmanagement.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseEntity, ID, R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {

    @Autowired
    R repository;
    @Override
    public List<T> getAll() {
        return repository.findAllByDeleteFlg(DeleteFlg.UNDELETE);
    }

    @Override
    public T create(T entity) {
        entity.setDeleteFlg(DeleteFlg.UNDELETE);
        return repository.save(entity);
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findOne(ID id) {
        return repository.findByIdAndDeleteFlg(id, DeleteFlg.UNDELETE);
    }

    @Override
    public void delete(T entity) {
        entity.setDeleteFlg(DeleteFlg.DELETED);
        repository.save(entity);
    }


}
