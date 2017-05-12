package com.dungeonstory.backend.service;

import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class TestPersistenceUnit {

    private static final String         PERSISTENCE_UNIT_HSQL_NAME = "dungeonstory-hsql2";
    private static final String         PERSISTENCE_UNIT_MYSQL_NAME = "dungeonstory-mysql";
    private static EntityManagerFactory factory;

    @Test
    public void testHsql() {
        try {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_HSQL_NAME);
            EntityManager em = factory.createEntityManager();
            // read the existing entries and write to console
            // Query q = em.createQuery("select t from Todo t");
            // List<Todo> todoList = q.getResultList();
            // for (Todo todo : todoList) {
            // System.out.println(todo);
            // }
            // System.out.println("Size: " + todoList.size());
            //
            // // create new todo
            // em.getTransaction().begin();
            // Todo todo = new Todo();
            // todo.setSummary("This is a test");
            // todo.setDescription("This is a test");
            // em.persist(todo);
            // em.getTransaction().commit();
            //
            em.close();
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }

    }

//    @Test
//    public void testMysql() {
//        try {
//            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_MYSQL_NAME);
//            EntityManager em = factory.createEntityManager();
//            em.close();
//        } catch (Exception e) {
//            fail(e.getLocalizedMessage());
//        }
//
//    }

}
