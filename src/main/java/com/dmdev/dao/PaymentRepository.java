package com.dmdev.dao;

import com.dmdev.entity.onetomany.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class PaymentRepository extends RepositoryBase<Long, Payment> {

//    public PaymentRepository(SessionFactory sessionFactory){
//        super(Payment.class, sessionFactory);
//    }


    public PaymentRepository(EntityManager entityManager) {
        super(Payment.class, entityManager);
    }

}
