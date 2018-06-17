package com.dungeonstory.backend.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.dungeonstory.backend.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

public class JPAService {
    private static EntityManagerFactory factory;
    private static EntityManager        entityManager;

    private static final String PERSISTENCE_UNIT_HSQL_NAME  = "dungeonstory-hsql2";
    private static final String PERSISTENCE_UNIT_MYSQL_NAME = "dungeonstory-mysql";

    public static void init() {
        if (factory == null || (factory != null && !factory.isOpen())) {
            if (Configuration.getInstance().getDatabaseType().equals("hsql")) {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_HSQL_NAME);
            } else {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_MYSQL_NAME);
            }
        }

        entityManager = factory.createEntityManager();
    }

    public static void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }

    public static EntityManagerFactory getFactory() {
        return factory;
    }

    public static <T> T get(Function<EntityManager, T> function) {
        try {
            T result = function.apply(entityManager);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public static <T> T getInTransaction(Function<EntityManager, T> function) {
        try {
            entityManager.getTransaction().begin();
            T result = function.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            rollback(entityManager.getTransaction());
            throw e;
        }
    }

    public static void executeInTransaction(Consumer<EntityManager> consumer) {
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            rollback(entityManager.getTransaction());
            throw e;
        }
    }

    private static void rollback(EntityTransaction transac) {
        if (transac != null && transac.isActive()) {
            transac.rollback();
        }
    }

}
