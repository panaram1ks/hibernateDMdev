package com.dmdev;

import com.dmdev.entity.onetomany.Payment;
import com.dmdev.entity.onetomany.User;
import com.dmdev.entity.onetoone.Profile;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

public class ListenerCallbackRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();

            Payment payment = session.find(Payment.class, 1L);

            session.getTransaction().commit();
        }
    }

}
