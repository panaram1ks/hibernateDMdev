package com.dmdev;

import com.dmdev.dao.CompanyRepository;
import com.dmdev.dao.UserRepository;
import com.dmdev.dto.UserCreateDto;
import com.dmdev.dto.UserReadDto;
import com.dmdev.entity.PersonalInfo;
import com.dmdev.entity.Role;
import com.dmdev.mapper.CompanyReadMapper;
import com.dmdev.mapper.UserCreateMapper;
import com.dmdev.mapper.UserReadMapper;
import com.dmdev.service.UserService;
import com.dmdev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
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
            CompanyRepository companyRepository = new CompanyRepository(proxySession);
            CompanyReadMapper companyReadMapper = new CompanyReadMapper();
            UserReadMapper userReadMapper = new UserReadMapper(companyReadMapper);
            UserCreateMapper userCreateMapper = new UserCreateMapper(companyRepository);
            UserService userService = new UserService(userRepository, userReadMapper, userCreateMapper);

//            userService.findById(1l).ifPresent(System.out::println);
            UserCreateDto userCreateDto = new UserCreateDto(
                    PersonalInfo.builder()
                            .firstname("Lisa")
                            .lastname("Stepanova")
                            .birthDate(LocalDate.now())
                            .build(),
                    "lisa@gmail.com",
                    null,
                    Role.USER,
                    1L
            );
            userService.create(userCreateDto);


            proxySession.getTransaction().commit();

        }
    }
}
