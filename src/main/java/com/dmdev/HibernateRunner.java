package com.dmdev;

import com.dmdev.entity.User;
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
//        configuration.configure("path/to/config.xml");
//        configuration.addAnnotatedClass(User.class);// first var mapping entity, second var add <mapping class= to xml >
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            System.out.println("ok");

            User user = User.builder()
                    .username("ivan@gmail.com")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDate(LocalDate.of(2000, 1, 19))
                    .age(22)
                    .build();

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit(); // if everything is goog
//            session.getTransaction().rollback(); // if something wrong


        }

    }

}
