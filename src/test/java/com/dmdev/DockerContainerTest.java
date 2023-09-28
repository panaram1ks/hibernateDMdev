package com.dmdev;

import com.dmdev.entity.onetomany.Company;
import com.dmdev.util.HibernateTestUtil;
//import com.dmdev.util.HibernateUtil !!!!!;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

public class DockerContainerTest {

    @Test
    void checkH2(){
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Company company = Company.builder()
                    .name("Google")
                    .build();
            session.save(company);

            transaction.commit();
        }
    }

}
