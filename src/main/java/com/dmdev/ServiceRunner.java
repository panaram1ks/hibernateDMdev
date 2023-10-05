package com.dmdev;

import com.dmdev.dao.UserRepository;
import com.dmdev.dto.UserReadDto;
import com.dmdev.mapper.CompanyReadMapper;
import com.dmdev.mapper.UserReadMapper;
import com.dmdev.service.UserService;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.Proxy;
import java.util.Optional;

public class ServiceRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {

            Session proxySession = (Session) Proxy.newProxyInstance(
                    SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)
            );
            proxySession.beginTransaction();

            UserRepository userRepository = new UserRepository(proxySession);
            CompanyReadMapper companyReadMapper = new CompanyReadMapper();
            UserReadMapper userReadMapper = new UserReadMapper(companyReadMapper);
            UserService userService = new UserService(userRepository, userReadMapper);
            userService.findById(1l).ifPresent(System.out::println);


            proxySession.getTransaction().commit();

        }
    }
}
