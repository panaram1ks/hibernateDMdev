package com.dmdev;

import com.dmdev.entity.manytomany.UserChat;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

public class SecontLevelCacheRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                User user = session.find(User.class, 1L);
                String name = user.getCompany().getName();
                List<UserChat> userChats = user.getUserChats();

                User user1 = session.find(User.class, 1L);

                session.getTransaction().commit();
            }

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                User user2 = session2.find(User.class, 1L);
                String name2 = user2.getCompany().getName();
                List<UserChat> userChats2 = user2.getUserChats();

                session2.getTransaction().commit();
            }

        }

    }

}
