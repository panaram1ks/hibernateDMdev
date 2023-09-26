package com.dmdev;

import com.dmdev.entity.onetomany.Company;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManyToOneRunner {

    public static void main(String[] args) {
        Company company = Company.builder().name("Intel").build();
        User user = User.builder()
                .username("vladimir15@gmail.com")
                .company(company)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.save(user);
//            User userFromDB = session.get(User.class, 1L);
//            session.evict(userFromDB);
//            session.save(company);
//            session.save(user);
            transaction.commit();
        }


    }
}
