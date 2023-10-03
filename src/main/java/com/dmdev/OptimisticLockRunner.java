package com.dmdev;

import com.dmdev.entity.onetomany.Payment;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

public class OptimisticLockRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
             Session session1 = sessionFactory.openSession()) {
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();
            session1.beginTransaction();

            Payment payment = session.find(Payment.class, 1L);
            payment.setAmount(payment.getAmount() + 10);

            Payment theSamePayment = session1.find(Payment.class, 1L, LockModeType.OPTIMISTIC);
            theSamePayment.setAmount(theSamePayment.getAmount() + 20);

            session.getTransaction().commit();
            session1.getTransaction().commit();
        }
    }

}
