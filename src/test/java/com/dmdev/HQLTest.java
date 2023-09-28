package com.dmdev;

import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateTestUtil;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import javax.persistence.NamedQuery;
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

////          HQL / JPQL
////          select * from users u where u.firstname = 'Ivan';
////            Query query = session.createQuery("SELECT u FROM User u WHERE u.personalInfo.firstname = ?1", User.class); // ?1 -> parameter // unrecommended way!
//            Query query = session.createQuery(
//                    "SELECT u FROM User u " +
////                    "JOIN u.company c " +
//                            "WHERE u.personalInfo.firstname = :firstname AND u.company.name = :companyName " +
//                            "ORDER BY u.personalInfo.lastname ASC"
//                    , User.class); // hidden JOIN!!!

            Query<User> namedQuery = session.createNamedQuery("findUserByNameAndCompanyName", User.class);

//            query.setParameter(1, "Ivan");
            namedQuery.setParameter("firstname", "Ivan");
            namedQuery.setParameter("companyName", "Google");

            namedQuery.setFlushMode(FlushMode.AUTO);
//            namedQuery.setHint(QueryHints.FLUSH_MODE, "commit");
            namedQuery.setHint(QueryHints.FETCH_SIZE, "50");

            int countRows = session.createQuery("UPDATE User u SET u.role = 'ADMIN'").executeUpdate();

            List<User> list = namedQuery.list();

            transaction.commit();
        }
    }


}
