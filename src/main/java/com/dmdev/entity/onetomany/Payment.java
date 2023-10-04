package com.dmdev.entity.onetomany;


import com.dmdev.entity.AuditableEntity;
import com.dmdev.entity.BaseEntityInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@OptimisticLocking(type = OptimisticLockType.VERSION)
//@OptimisticLocking(type = OptimisticLockType.ALL)
//@DynamicUpdate
//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Audited
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Payment extends AuditableEntity<Long> implements BaseEntityInterface<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

//    @NotAudited
    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @Version
    private Long version;


    // It's bad control callbacks into entity that's why we should use Listeners
//    @PrePersist
//    public void prePersist(){
//        setCreatedAt(Instant.now());
////        setAddedBy();
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        setUpdatedAt(Instant.now());
////        setUpdatedBy(); fill from security context
//    }


}
