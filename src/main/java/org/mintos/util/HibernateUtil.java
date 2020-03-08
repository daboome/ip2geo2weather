package org.mintos.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.mintos.model.IdentifiableEntity;

public enum HibernateUtil {

    INSTANCE;

    private final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable throwable) {
            System.out.println("Initial SessionFactory creation failed");
            throw new ExceptionInInitializerError(throwable);
        }
    }

    public SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public void saveOrUpdateInTransaction(IdentifiableEntity entity) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
    }
}
