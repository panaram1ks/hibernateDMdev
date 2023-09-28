package com.dmdev;

import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HQLTest {

    @Test
    void checkHql() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = User.builder()
                    .personalInfo(PersonalInfo.builder()
                            .firstname("Ivan")
                            .build())
                    .build();
            session.save(user);
            session.flush();
            session.clear();

//          HQL / JPQL
//          select * from users u where u.firstname = 'Ivan';
//            Query query = session.createQuery("SELECT u FROM User u WHERE u.personalInfo.firstname = ?1", User.class); // ?1 -> parameter // unrecommended way!
            Query query = session.createQuery("SELECT u FROM User u WHERE u.personalInfo.firstname = :firstname", User.class); // second way
//            query.setParameter(1, "Ivan");
            query.setParameter("firstname", "Ivan");
            List<User> list = query.list();

            transaction.commit();
        }
    }


}
