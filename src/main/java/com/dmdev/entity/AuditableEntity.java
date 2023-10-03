package com.dmdev.entity;

import com.dmdev.listener.AuditListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditListener.class})
public abstract class AuditableEntity<T extends Serializable> implements BaseEntityInterface<T>{

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "added_by")
    private String addedBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

}
