package com.dmdev.dao;

import com.dmdev.entity.BaseEntityInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntityInterface<K>> implements Repository<K, E> {

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
    public Optional<E> findById(K id, Map<String, Object> properties) {
//        Session session = sessionFactory.getCurrentSession();
//        return Optional.ofNullable(session.find(clazz, id));
        return Optional.ofNullable(entityManager.find(clazz, id, properties));
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
