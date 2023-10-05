package com.dmdev;

import com.dmdev.dao.PaymentRepository;
import com.dmdev.dao.UserRepository;
import com.dmdev.mapper.CompanyReadMapper;
import com.dmdev.mapper.UserReadMapper;
import com.dmdev.service.UserService;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DaoRepositoryRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
//            Session session = sessionFactory.getCurrentSession();

            Session proxySession = (Session) Proxy.newProxyInstance(
                    SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)
            );
//            session.beginTransaction();
            proxySession.beginTransaction();

            UserRepository userRepository = new UserRepository(proxySession);
            CompanyReadMapper companyReadMapper = new CompanyReadMapper();
            UserReadMapper userReadMapper = new UserReadMapper(companyReadMapper);
//            UserService userService = new UserService(userRepository, userReadMapper);

//            PaymentRepository paymentRepository = new PaymentRepository(sessionFactory);
//            PaymentRepository paymentRepository = new PaymentRepository(proxySession);
//            paymentRepository.findById(1L).ifPresent(System.out::println);

//            session.getTransaction().commit();
            proxySession.getTransaction().commit();

        }
    }
}
