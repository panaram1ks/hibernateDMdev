package com.dmdev;

import com.dmdev.entity.Role;
import com.dmdev.entity.onetomany.Company;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

class HibernateRunnerTest {

    @Test
    void deleteUser(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        User user = session.get(User.class, 3L);
        session.delete(user);

        transaction.commit();
    }

    @Test
    void deleteCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        Company company = session.get(Company.class, 6L);
        session.delete(company);

        transaction.commit();
    }

    @Test
    void addUserToNewCompany(){
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = Company.builder()
                .name("Facebook")
                .build();
        User user = User.builder()
                .username("sveta@gmail.com")
                .build();
//        user.setCompany(company);
//        company.getUsers().add(user);
        company.addUser(user);
        session.save(company);
        transaction.commit();
    }

    @Test
    void oneToMany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Company company = session.get(Company.class, 3L);
        System.out.println();

        transaction.commit();
    }

//    @Test
//    void checkGetReflectionAPI() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = preparedStatement.executeQuery();
//        resultSet.getString("username");
//        resultSet.getString("lastname");
//
//        Class<User> clazz = User.class;
//        Constructor<User> constructor = clazz.getDeclaredConstructor();
//        User user = constructor.newInstance();
//        Field usernameField = clazz.getDeclaredField("username");
//        usernameField.setAccessible(true);
//        usernameField.set(user, resultSet.getString("username"));
//
//
//    }
//
//    @Test
//    void checkReflectionApi() throws SQLException, IllegalAccessException {
//        User user = User.builder()
//                .username("ivan@gmail.com")
////                .firstname("Ivan")
////                .lastname("Ivanov")
//                .role(Role.ADMIN)
//                .build();
//
//        String sql = """
//                    insert
//                    into
//                        %s
//                        (%s)
//                    values
//                        (%s)
//                """;
//        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
//                .orElse(user.getClass().getName());
//
//        Field[] declaredFields = user.getClass().getDeclaredFields();
//
//        String columnNames = Arrays.stream(declaredFields)
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName()))
//                .collect(joining(", "));
//
//        String columnValues = Arrays.stream(declaredFields)
//                .map(field -> "?")
//                .collect(joining(", "));
//
//        System.out.println(sql.formatted(tableName, columnNames, columnValues));
//
//        String sqlRequest = sql.formatted(tableName, columnNames, columnValues);
//        Connection connection = null;
//        PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
//        for (Field declaredField : declaredFields) {
//            declaredField.setAccessible(true);
//            preparedStatement.setObject(1, declaredField.get(user));
//
//        }
//    }
}