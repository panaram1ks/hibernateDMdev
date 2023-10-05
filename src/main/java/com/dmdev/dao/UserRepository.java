package com.dmdev.dao;


import com.dmdev.entity.onetomany.User;

import javax.persistence.EntityManager;

public class UserRepository extends RepositoryBase<Long, User>{

    public UserRepository(EntityManager entityManager){
        super(User.class, entityManager);
    }


}
