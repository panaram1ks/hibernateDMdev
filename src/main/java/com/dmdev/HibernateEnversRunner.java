package com.dmdev;

import com.dmdev.entity.onetomany.Payment;
import com.dmdev.util.HibernateUtil;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public class HibernateEnversRunner {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                Payment payment = session.find(Payment.class, 1L);
                payment.setAmount(800);

                session.getTransaction().commit();
            }

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                AuditReader auditReader = AuditReaderFactory.get(session2);
                List resultList = auditReader.createQuery()
                        .forEntitiesAtRevision(Payment.class, 400l)
                        .add(AuditEntity.property("amount").ge(450))
                        .add(AuditEntity.property("id").ge(6l))
//                        .addProjection(AuditEntity.property("amount"))
//                        .addProjection(AuditEntity.property("id"))
                        .getResultList();
                Payment oldPayment = auditReader.find(Payment.class, 1L, new Date(1696399409386l));
                System.out.println();
                session2.replicate(oldPayment, ReplicationMode.OVERWRITE); // overwrite payment in database to old payment object condition

                session2.getTransaction().commit();
            }

        }
    }
}
