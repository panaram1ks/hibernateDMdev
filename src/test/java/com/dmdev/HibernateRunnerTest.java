package com.dmdev;

import com.dmdev.entity.onetomany.Company;
import com.dmdev.entity.onetomany.User;
import com.dmdev.entity.onetoone.Profile;
import com.dmdev.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

class HibernateRunnerTest {

    @Test
    void checkOneToOne() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
//            User user = User.builder()
//                    .username("test25@gmail.com")
//                    .build();
//            Profile profile = Profile.builder()
//                    .language("ru")
//                    .street("Kolasa 25")
//                    .build();
//            profile.setUser(user);
//
//            session.save(user);
//            profile.setUser(user);
//            session.save(profile);

            User user = session.get(User.class, 4l);

            transaction.commit();
        }
    }


    @Test
    void checkOrhanRemoval() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Company company = session.get(Company.class, 14l);
//        Set<User> users = company.getUsers();
//        users.removeIf(user -> user.getId().equals(1L));

        transaction.commit();
    }


    @Test
    void saveUsers() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String companiesQuery = "SELECT id, name  FROM company";
        NativeQuery nativeQuery = session.createNativeQuery(companiesQuery);
        List<Company> resultList = (List<Company>) nativeQuery.getResultList();

        transaction.commit();
    }

    @Test
    void saveCompanies() {
        Company google = Company.builder()
                .name("Google")
                .build();
        Company amazone = Company.builder()
                .name("Amazone")
                .build();
        Company facebook = Company.builder()
                .name("Facebook")
                .build();

        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(google);
        session.save(amazone);
        session.save(facebook);

        transaction.commit();
    }

    @Test
    void checkLazyInitialization() {
        Company company = null;
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            company = session.get(Company.class, 12L);

            transaction.commit();
        }
//        Set<User> users = company.getUsers();
//        System.out.println(users.size());
    }

    @Test
    void getCompanyById() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Company company = session.get(Company.class, 1L);
        Hibernate.initialize(company.getUsers());
        System.out.println();

        transaction.commit();
    }

    @Test
    void deleteUser() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 3L);
        session.delete(user);

        transaction.commit();
    }

    @Test
    void deleteCompany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Company company = session.get(Company.class, 6L);
        session.delete(company);

        transaction.commit();
    }

    @Test
    void addUserToNewCompany() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = Company.builder()
                .name("OZONE")
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
        Hibernate.initialize(company.getUsers());
        System.out.println(company.getUsers());

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