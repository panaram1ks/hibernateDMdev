package com.dmdev;


import com.dmdev.entity.onetomany.Payment;
import com.dmdev.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class TransactionsRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.doWork(connection -> System.out.println(connection.getTransactionIsolation()));
//                    connection.setTransactionIsolation(); // 1,2,4,8
//            try {
//                Transaction transaction = session.beginTransaction();
//
//                var payment = session.find(Payment.class, 1L);
//
//                transaction.commit();
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                throw e;
//            }

        }


    }



}
