package com.dmdev;

import com.dmdev.entity.Birthday;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

/**
 * @author E.Parominsky 25/09/2023 16:12
 */
@Slf4j
//@Log4j
public class HibernateRunner2 {

    public static void main(String[] args) {
        User user = User.builder()
                .username("petr3@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .lastname("Petr")
                        .firstname("Retrov")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 2)))
                        .build())
                .build();
        //user is Transient;
//        log.info("user entity is in transient state, object: " + user);
        log.info("user entity is in transient state, object: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session1 = sessionFactory.openSession()) {
            Transaction transaction = session1.beginTransaction();
            log.trace("Transaction is created, {}", transaction);

            session1.saveOrUpdate(user); // Persistent
            log.trace("User is in persistent state: {}, session {}", user, session1);

            session1.getTransaction().commit();

            try(Session session = sessionFactory.openSession()){

                PersonalInfo key = PersonalInfo.builder()
                        .lastname("Petr")
                        .firstname("Retrov")
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 2)))
                        .build();

                User user1 = session.get(User.class, key);
                System.out.println();
            }
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
        log.warn("User is in detached state: {}, session is closed", user);

//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
//             Session session2 = sessionFactory.openSession()) {
//            session2.beginTransaction();
//
//            user.setPersonalInfo(
//                    PersonalInfo.builder()
//                            .firstname("Sveta")
//                            .build());
////            session2.delete(user);
////            refresh/merge
////            session2.refresh(user); // 1) freshUser = session2.get(User.class, user.getUsername()); 2) set all data from freshUser from db to our user in program
//            session2.merge(user); // 1) mergedUser = session2.get(User.class, user.getUsername()); 2) set all data from mergedUser to user in db
//
//            session2.getTransaction().commit();
//        }

    }
}