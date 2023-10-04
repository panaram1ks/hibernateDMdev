package com.dmdev.dao;

import com.dmdev.entity.onetomany.Payment;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class PaymentDao implements Dao<Long, Payment> {

    private final SessionFactory sessionFactory;

    @Override
    public Payment save(Payment entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        @Cleanup Session session = sessionFactory.openSession();
        session.delete(id);
        session.flush();
    }

    @Override
    public void update(Payment entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.merge(entity);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        @Cleanup Session session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(Payment.class, id));
    }

    @Override
    public List<Payment> findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        return session.createQuery("select p from Payment p", Payment.class).getResultList();
    }
}
