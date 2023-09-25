package com.dmdev;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.Birthday;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.BlockingDeque;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
//        BlockingDeque<Connection> pool = null;
////        Connection connection = pool.take();
//        SessionFactory


//        Session
//        DriverManager.getConnection("db.url", "db.username", "db.password");


        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());// first var , second var @Column above field
//        configuration.addAttributeConverter(new BirthdayConverter(), true);// second var say Hibernate use this converter
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());


//        configuration.configure("path/to/config.xml");
//        configuration.addAnnotatedClass(User.class);// first var mapping entity, second var add <mapping class= to xml >
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            System.out.println("ok");

            User user = User.builder()
                    .username("ivan3@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(new Birthday(LocalDate.of(2000, 1, 19)))
                    .role(Role.ADMIN)
                    .info("""
                            {
                                "name": "Ivan",
                                "id": 25
                            }
                            """)
                    .build();

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit(); // if everything is goog
//            session.getTransaction().rollback(); // if something wrong


        }

    }

}
