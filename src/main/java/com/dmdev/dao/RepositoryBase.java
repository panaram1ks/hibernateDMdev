package com.dmdev.dao;

import com.dmdev.entity.BaseEntityInterface;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntityInterface<K>>  implements Dao<K, E> {

    private final Class<E> clazz;
    private final SessionFactory sessionFactory;

    @Override
    public E save(E entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        @Cleanup Session session = sessionFactory.openSession();
        session.delete(id);
        session.flush();
    }

    @Override
    public void update(E entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        @Cleanup Session session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);
        criteria.from(clazz);
        return session.createQuery(criteria).getResultList();
    }
}
