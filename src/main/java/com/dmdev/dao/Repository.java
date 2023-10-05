package com.dmdev.dao;

import com.dmdev.entity.BaseEntityInterface;

import java.io.Serializable;
import java.util.*;

public interface Repository<K extends Serializable, E extends BaseEntityInterface> {

    E save(E entity);

    void delete(K id);

    void update(E entity);

   default Optional<E> findById(K id){
       return findById(id, Collections.emptyMap());
   };

    Optional<E> findById(K id, Map<String, Object> properties);

    List<E> findAll();
}
