package com.dmdev.listener;

import com.dmdev.entity.AuditableEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

public class AuditListener {

    @PrePersist
    public void prePersist(AuditableEntity<?> entity){
        entity.setCreatedAt(Instant.now());
//        setAddedBy();
    }

    @PreUpdate
    public void preUpdate(AuditableEntity<?> entity){
        entity.setUpdatedAt(Instant.now());
//        setUpdatedBy(); fill from security context
    }
}
