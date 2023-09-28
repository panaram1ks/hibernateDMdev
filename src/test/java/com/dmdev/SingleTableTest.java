package com.dmdev;


import com.dmdev.entity.onetomany.Company;
import com.dmdev.entity.onetomany.User;
import com.dmdev.entity.tableperclass.Language;
import com.dmdev.entity.tableperclass.Manager;
import com.dmdev.entity.tableperclass.Programmer;
import com.dmdev.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

public class SingleTableTest {

    @Test
    void checkMappingProblems(){
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Company google = Company.builder()
                    .name("Google")
                    .build();
            session.save(google);
//
//            Programmer programmer = Programmer.builder()
//                    .username("ivan@gmail.com")
//                    .language(Language.JAVA)
//                    .company(google)
//                    .build();
//            session.save(programmer);
//            Manager manager = Manager.builder()
//                    .username("manager@gmail.com")
//                    .company(google)
//                    .projectName("manager-project")
//                    .build();
//            session.save(manager);

            session.flush();

            session.clear();

            Programmer programmer1 = session.get(Programmer.class, 1L);
            User manager1 = session.get(User.class, 2L);


            transaction.commit();
        }
    }
}
