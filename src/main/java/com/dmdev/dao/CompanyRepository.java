package com.dmdev.dao;

import com.dmdev.entity.onetomany.Company;
import org.hibernate.SessionFactory;

public class CompanyRepository extends RepositoryBase<Long, Company> {

    public CompanyRepository(SessionFactory sessionFactory){
        super(Company.class, sessionFactory);
    }
}
