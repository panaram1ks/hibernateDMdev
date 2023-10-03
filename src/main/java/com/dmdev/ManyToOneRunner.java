package com.dmdev;

import com.dmdev.entity.manytomany.UserChat;
import com.dmdev.entity.onetomany.User;
import com.dmdev.util.HibernateUtil;
import com.dmdev.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.QueryHints;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;

import java.util.List;
import java.util.Map;

public class ManyToOneRunner {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
//            TestDataImporter.importData(sessionFactory);
            Transaction transaction = session.beginTransaction();
//            session.enableFetchProfile("withCompany"); // fetch profile works only with separate entity
//            session.enableFetchProfile("withCompanyAndPayment"); // fetch profile works only with separate entity

            RootGraph<User> userGraph = session.createEntityGraph(User.class);
            userGraph.addAttributeNodes("company", "userChats");
            SubGraph<UserChat> userChatSubGraph = userGraph.addSubGraph("userChats", UserChat.class);
            userChatSubGraph.addAttributeNodes("chat");


            RootGraph<?> graph = session.getEntityGraph("WithCompanyAndChat");

            Map<String, Object> properties = Map.of(GraphSemantic.LOAD.getJpaHintName(), userGraph );
            var user = session.find(User.class, 1L, properties);

//            User user = session.get(User.class, 1L);
//            System.out.println(user.getPayments().size());
            System.out.println(user.getCompany().getName());
            System.out.println(user.getUserChats().size());

            List<User> users = session.createQuery("select u from User u join fetch u.payments join fetch u.company where 1 = 1", User.class)
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph )
                    .list();
            users.forEach(it -> System.out.println(it.getPayments().size()));
            users.forEach(it -> System.out.println(it.getCompany().getName()));

            transaction.commit();
        }
    }
}
