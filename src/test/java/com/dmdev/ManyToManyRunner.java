package com.dmdev;

import com.dmdev.entity.manytomany.Chat;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

public class ManyToManyRunner {

    @Test
    void checkManyToMany() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, 4L);
            user.getChats().clear();
//            Chat chat = Chat.builder()
//                    .name("dmdevchat")
//                    .build();
//            session.save(chat);
            transaction.commit();
        }
    }



}
