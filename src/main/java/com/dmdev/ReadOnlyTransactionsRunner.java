package com.dmdev;

import com.dmdev.entity.onetomany.Payment;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

public class ReadOnlyTransactionsRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            TestDataImporter.importData(sessionFactory);



            // Application level set read only mod
//            session.setDefaultReadOnly(true); // set read only for everywhere !
//            session.setReadOnly( Entity );

            session.beginTransaction();
            // Database level set read only mod
            session.createNativeQuery("SET TRANSACTION READ ONLY;").executeUpdate();

//            session.createQuery("select  p from Payment  p", Payment.class)
//                    // Application level set read only mod
//                    .setReadOnly(true) // or
////                    .setHint(QueryHints.READ_ONLY, true)
//                    .list();

            Payment payment = session.find(Payment.class, 1L);
            payment.setAmount(payment.getAmount() + 10);

            session.getTransaction().commit();
        }
    }

}
