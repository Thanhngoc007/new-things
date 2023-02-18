package fa.training.studentmanagement.entity;

import fa.training.studentmanagement.enums.DeleteFlg;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends AbstractAuditingEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "delete_flg")
    private DeleteFlg deleteFlg; // delete logical
}
