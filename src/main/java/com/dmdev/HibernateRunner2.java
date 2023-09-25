package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author E.Parominsky 25/09/2023 16:12
 */
public class HibernateRunner2 {

    public static void main(String[] args) {
        User user = User.builder()
                .username("ivan@gmail.com")
                .lastname("Ivanov")
                .firstname("Ivan")
                .build();
        //user is Transient;

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session1 = sessionFactory.openSession()) {
            session1.beginTransaction();

            session1.saveOrUpdate(user); // Persistent

            session1.getTransaction().commit();
        }

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session2 = sessionFactory.openSession()) {
            session2.beginTransaction();

            user.setFirstname("Sveta");
//            session2.delete(user);
//            refresh/merge
//            session2.refresh(user); // 1) freshUser = session2.get(User.class, user.getUsername()); 2) set all data from freshUser from db to our user in program
            session2.merge(user); // 1) mergedUser = session2.get(User.class, user.getUsername()); 2) set all data from mergedUser to user in db

            session2.getTransaction().commit();
        }
    }
}