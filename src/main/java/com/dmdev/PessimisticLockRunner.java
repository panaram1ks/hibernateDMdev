package com.dmdev;

import com.dmdev.entity.onetomany.Payment;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

public class PessimisticLockRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
             Session session1 = sessionFactory.openSession()) {
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();
            session1.beginTransaction();

//            Payment payment = session.find(Payment.class, 1L, LockModeType.PESSIMISTIC_READ);
//            Payment payment = session.find(Payment.class, 1L, LockModeType.PESSIMISTIC_WRITE);
            session.createQuery("select  p from Payment  p", Payment.class).setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
                    .setHint("javax.persistence.lock.timeout", 5000);

            Payment payment = session.find(Payment.class, 1L, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
//           session.get();
            payment.setAmount(payment.getAmount() + 10);

            session1.getTransaction().commit();
            session.getTransaction().commit();
        }
    }

}
