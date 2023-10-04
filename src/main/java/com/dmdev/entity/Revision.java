package com.dmdev.entity;

import com.dmdev.listener.MyRevisionListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@RevisionEntity(MyRevisionListener.class) // Only one revision entity able to exist in project !
public class Revision {

    //  @RevisionNumber, @RevisionTimestamp - two general field in revision and it required !
    @RevisionNumber
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @RevisionTimestamp
    private Long timestamp;


    private String username;

}
