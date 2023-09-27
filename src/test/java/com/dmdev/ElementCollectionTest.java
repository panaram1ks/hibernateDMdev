package com.dmdev;

import com.dmdev.entity.LocaleInfo;
import com.dmdev.entity.onetomany.Company;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ElementCollectionTest {

    @Test
    void checkOneToOne() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Company company = session.get(Company.class, 10l);
//            company.getLocales().add(LocaleInfo.of("ru", "Описание на русском"));
//            company.getLocales().add(LocaleInfo.of("en", "English description"));

//            Set<User> users = company.getUsers();
            Map<String, User> users = company.getUsers();
            users.forEach((k, v) -> System.out.println(v));

            transaction.commit();
        }
    }

}
