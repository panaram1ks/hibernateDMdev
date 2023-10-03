package com.dmdev;

import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ManyToOneRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
//            TestDataImporter.importData(sessionFactory);
            Transaction transaction = session.beginTransaction();
//            session.enableFetchProfile("withCompany"); // fetch profile works only with separate entity
            session.enableFetchProfile("withCompanyAndPayment"); // fetch profile works only with separate entity
            User user = session.get(User.class, 1L);
//            System.out.println(user.getPayments().size());
            System.out.println(user.getCompany().getName());

//            List<User> users = session.createQuery("select u from User u join fetch u.payments join fetch u.company where 1 = 1", User.class)
//                    .list();
//            users.forEach(user -> System.out.println(user.getPayments().size()));
//            users.forEach(user -> System.out.println(user.getCompany().getName()));

            transaction.commit();
        }
    }
}
