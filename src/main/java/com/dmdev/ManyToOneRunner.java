package com.dmdev;

import com.dmdev.entity.Birthday;
import com.dmdev.entity.Company;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class ManyToOneRunner {

    public static void main(String[] args) {
        Company company = Company.builder().name("Google").build();
        User user = User.builder()
                .username("petr3@gmail.com")
                .company(company)
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.save(company);
            session.save(user);

            session.getTransaction().commit();
        }

    }
}
