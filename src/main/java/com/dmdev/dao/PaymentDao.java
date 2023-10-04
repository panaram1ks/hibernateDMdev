package com.dmdev.dao;

import com.dmdev.entity.onetomany.Payment;
import org.hibernate.SessionFactory;

public class PaymentDao extends BaseDao<Long, Payment> {

    public PaymentDao(SessionFactory sessionFactory){
        super(Payment.class, sessionFactory);
    }
}
