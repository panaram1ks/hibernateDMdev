package com.dmdev;

import com.dmdev.dao.CompanyRepository;
import com.dmdev.dao.UserRepository;
import com.dmdev.dto.UserCreateDto;
import com.dmdev.dto.UserReadDto;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.inercepter.TransactionalInterceptor;
import com.dmdev.mapper.CompanyReadMapper;
import com.dmdev.mapper.UserCreateMapper;
import com.dmdev.mapper.UserReadMapper;
import com.dmdev.service.UserService;
import com.dmdev.util.HibernateUtil;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Optional;

public class ServiceRunner {

    @Transactional
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {

            Session proxySession = (Session) Proxy.newProxyInstance(
                    SessionFactory.class.getClassLoader(),
                    new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1)
            );
//            proxySession.beginTransaction();

            UserRepository userRepository = new UserRepository(proxySession);
            CompanyRepository companyRepository = new CompanyRepository(proxySession);
            CompanyReadMapper companyReadMapper = new CompanyReadMapper();
            UserReadMapper userReadMapper = new UserReadMapper(companyReadMapper);
            UserCreateMapper userCreateMapper = new UserCreateMapper(companyRepository);
//            UserService userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            TransactionalInterceptor transactionalInterceptor = new TransactionalInterceptor(sessionFactory);
            UserService userService1 = new ByteBuddy()
                    .subclass(UserService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionalInterceptor))
                    .make()
                    .load(UserService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(UserRepository.class, UserReadMapper.class, UserCreateMapper.class)
                    .newInstance(userRepository, userReadMapper, userCreateMapper);

//            userService.findById(1l).ifPresent(System.out::println);
            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Lisa22")
                            .lastname("Stepanova")
                            .birthDate(LocalDate.now())
                            .build(),
                    "lisa22@gmail.com",
                    null,
                    Role.USER,
                    1L
            );
            userService1.create(userCreateDto);
//            userService.create(userCreateDto);


//            proxySession.getTransaction().commit();

        }
    }
}
