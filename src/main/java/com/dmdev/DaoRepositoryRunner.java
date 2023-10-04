package com.dmdev;

import com.dmdev.dao.PaymentRepository;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

public class DaoRepositoryRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                PaymentRepository paymentRepository = new PaymentRepository(sessionFactory);
                paymentRepository.findById(1L).ifPresent(System.out::println);

                session.getTransaction().commit();
            }

        }
    }
}
