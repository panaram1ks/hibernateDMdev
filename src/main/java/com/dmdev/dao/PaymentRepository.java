package com.dmdev.dao;

import com.dmdev.entity.onetomany.Payment;
import org.hibernate.SessionFactory;

public class PaymentRepository extends RepositoryBase<Long, Payment> {

    public PaymentRepository(SessionFactory sessionFactory){
        super(Payment.class, sessionFactory);
    }
}
