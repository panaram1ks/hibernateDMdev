package com.dmdev;

import com.dmdev.entity.onetomany.Payment;
import com.dmdev.entity.onetomany.User;
import com.dmdev.entity.onetoone.Profile;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

public class NontransactionalDataAccessRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            TestDataImporter.importData(sessionFactory);

            var profile = Profile.builder()
                    .user(session.find(User.class, 1L))
                    .language("ru")
                    .street("Kolasa 28")
                    .build();
            session.save(profile);

            session.createQuery("select  p from Payment  p", Payment.class)
                    .list();
            Payment payment = session.find(Payment.class, 1L);
        }
    }

}
