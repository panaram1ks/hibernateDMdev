package com.dmdev.dao;

import com.dmdev.entity.onetomany.Company;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class CompanyRepository extends RepositoryBase<Long, Company> {

//    public CompanyRepository(SessionFactory sessionFactory){
//        super(Company.class, sessionFactory);
//    }
    public CompanyRepository(EntityManager entityManager){
        super(Company.class, entityManager);
    }
}
