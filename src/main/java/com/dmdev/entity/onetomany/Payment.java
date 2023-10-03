package com.dmdev.entity.onetomany;


import com.dmdev.entity.BaseEntityInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@OptimisticLocking(type = OptimisticLockType.VERSION)
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Payment implements BaseEntityInterface<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

//    @Version
//    private Long version;


}
