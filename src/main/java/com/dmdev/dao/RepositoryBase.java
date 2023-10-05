package com.dmdev.dao;

import com.dmdev.entity.BaseEntityInterface;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntityInterface<K>> implements Dao<K, E> {

    private final Class<E> clazz;
    //    private final SessionFactory sessionFactory;
//    private final Session session;
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(entity);
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.delete(id);
//        session.flush();
        entityManager.remove(id);
        entityManager.flush();
    }

    @Override
    public void update(E entity) {
//        Session session = sessionFactory.getCurrentSession();
//        session.merge(entity);
        entityManager.merge(entity);

    }

    @Override
    public Optional<E> findById(K id) {
//        Session session = sessionFactory.getCurrentSession();
//        return Optional.ofNullable(session.find(clazz, id));
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
//        Session session = sessionFactory.getCurrentSession();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);
        criteria.from(clazz);
        return entityManager.createQuery(criteria).getResultList();
    }
}
