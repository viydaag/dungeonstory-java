package com.dungeonstory.backend.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.QueryHints;

import com.dungeonstory.backend.Configuration;

public abstract class AbstractRepository<E extends Entity, K extends Serializable> implements Repository<E, K>, Serializable {

    private static final long serialVersionUID = 2825199262413194217L;

    private static final String PERSISTENCE_UNIT_HSQL_NAME  = "dungeonstory-hsql2";
    private static final String PERSISTENCE_UNIT_MYSQL_NAME = "dungeonstory-mysql";

    protected static EntityManagerFactory factory;
    protected static EntityManager        entityManager;

    private String tableName;

    public AbstractRepository() {
        super();
        if (Configuration.getInstance().getDatabaseType().equals("hsql")) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_HSQL_NAME);
        } else {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_MYSQL_NAME);
        }

        entityManager = factory.createEntityManager();
    }

    private void rollback(EntityTransaction transac) {
        if (transac != null && transac.isActive()) {
            transac.rollback();
        }
    }

    @Override
    public synchronized void create(E entity) {
        EntityTransaction transac = entityManager.getTransaction();
        transac.begin();
        try {
            entityManager.persist(entity);
            transac.commit();
        } catch (Exception e) {
            rollback(transac);
            throw e;
        }
    }

    /**
     * If the ID of the object is null the delete action is skipped.
     *
     * @param entity
     */
    @Override
    public synchronized void delete(E entity) {
        if (entity.getId() == null) {
            return;
        }
        EntityTransaction transac = entityManager.getTransaction();
        transac.begin();
        try {
            E entity2 = entityManager.getReference(getEntityClass(), entity.getId());
            entityManager.remove(entity2);
            transac.commit();
        } catch (Exception e) {
            rollback(transac);
            throw e;
        }

    }

    /**
     * If the key is null the delete action is skipped.
     *
     * @param key
     */
    @Override
    public synchronized void delete(K key) {
        if (key == null) {
            return;
        }

        EntityTransaction transac = entityManager.getTransaction();
        transac.begin();
        try {
            Query query = entityManager.createQuery("DELETE FROM " + getTableName() + " o WHERE o.id = :id");
            query.setParameter("id", key);
            query.executeUpdate();
            transac.commit();
        } catch (Exception e) {
            rollback(transac);
            throw e;
        }
    }

    private String getTableName() {
        if (tableName == null) {
            tableName = getEntityClass().getSimpleName();
            //            Annotation annotation = getEntityClass().getAnnotation(Table.class);
            //            if (annotation != null) {
            //                Table tableAnnotation = (Table) annotation;
            //                tableName = tableAnnotation.name();
            //            }
        }
        return tableName;
    }

    @Override
    public synchronized void delete(Collection<E> entitySet) {
        for (E entity : entitySet) {
            delete(entity);
        }
    }

    @Override
    public List<E> findAll() {
        TypedQuery<E> query = entityManager.createQuery("SELECT o FROM " + getTableName() + " o", getEntityClass());
        return query.getResultList();
    }

    @Override
    public List<E> findAllWithAttributes(String... attributes) {
        //        String sql = "SELECT o FROM " + getTableName() + " o";

        EntityGraph<E> graph = entityManager.createEntityGraph(getEntityClass());
        graph.addAttributeNodes(attributes);

        //        TypedQuery<E> query = entityManager.createQuery(sql, getEntityClass());
        //        query.setHint("javax.persistence.loadgraph", graph);

        //        TypedQuery<E> query = entityManager.createQuery(sql, getEntityClass());
        //        LoadGroup lg = new LoadGroup();
        //        lg.addAttributes(Arrays.asList(attributes));
        //        query.setHint(QueryHints.LOAD_GROUP, lg);
        //        for (String att : attributes) {
        //            query.setHint(QueryHints.BATCH, "o." + att);
        //        }

        //        List<E> result = query.getResultList();
        List<E> result = findAllWithGraph(graph);
        return result;
    }

    @Override
    public List<E> findAllWithGraph(EntityGraph<E> graph) {
        String sql = "SELECT o FROM " + getTableName() + " o";

        TypedQuery<E> query = entityManager.createQuery(sql, getEntityClass());

        query.setHint(QueryHints.JPA_LOAD_GRAPH, graph);
        return query.getResultList();
    }

    @Override
    public List<E> findAllOrderBy(String[] orderColumn, String[] order) {
        String orderQuery = getOrderQuery(orderColumn, order);
        TypedQuery<E> query = entityManager.createQuery("SELECT o FROM " + getTableName() + " o ORDER BY " + orderQuery, getEntityClass());
        return query.getResultList();
    }

    private String getOrderQuery(String[] orderColumn, String[] order) {
        String orderQuery = "";
        for (int i = 0; i < orderColumn.length; i++) {
            String orderCol = orderColumn[i];
            orderQuery += ("o." + orderCol + " " + order[i]);
            if (i != orderColumn.length - 1) {
                orderQuery += ", ";
            }
        }
        return orderQuery;
    }

    @Override
    public List<E> findAllBy(String column, String value) {
        TypedQuery<E> query = entityManager.createQuery("SELECT o FROM " + getTableName() + " o WHERE o." + column + " = :value", getEntityClass());
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public List<E> findAllByLike(String column, String value) {
        TypedQuery<E> query = entityManager.createQuery("SELECT o FROM " + getTableName() + " o WHERE o." + column + " LIKE :value",
                getEntityClass());
        query.setParameter("value", "%" + value + "%");
        return query.getResultList();
    }

    @Override
    public List<E> findAllByLikePaged(String column, String value, int firstRow, int pageSize) {
        TypedQuery<E> query = entityManager.createQuery("SELECT o FROM " + getTableName() + " o WHERE o." + column + " LIKE :value", getEntityClass())
                .setFirstResult(firstRow).setMaxResults(pageSize);
        query.setParameter("value", "%" + value + "%");
        return query.getResultList();
    }

    @Override
    public List<E> findAllPaged(int firstRow, int pageSize) {
        TypedQuery<E> query = entityManager.createQuery("SELECT o FROM " + getTableName() + " o", getEntityClass()).setFirstResult(firstRow)
                .setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<E> findAllPagedOrderBy(int firstRow, int pageSize, String[] orderColumn, String[] order) {
        String orderQuery = getOrderQuery(orderColumn, order);
        TypedQuery<E> query = entityManager.createQuery("SELECT o FROM " + getTableName() + " o ORDER BY " + orderQuery, getEntityClass())
                .setFirstResult(firstRow).setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<E> findAllByLikePagedOrderBy(String column, String value, int firstRow, int pageSize, String[] orderColumn, String[] order) {

        String orderQuery = getOrderQuery(orderColumn, order);
        TypedQuery<E> query = entityManager
                .createQuery("SELECT o FROM " + getTableName() + " o WHERE o." + column + " LIKE :value ORDER BY " + orderQuery, getEntityClass())
                .setFirstResult(firstRow).setMaxResults(pageSize);
        query.setParameter("value", "%" + value + "%");
        return query.getResultList();
    }

    protected abstract Class<E> getEntityClass();

    @Override
    public E read(K key) {
        E result = entityManager.find(getEntityClass(), key);
        return result;
    }

    @Override
    public synchronized void refresh(E entity) {
        entityManager.refresh(entity);
    }

    @Override
    public E saveOrUpdate(E entity) {
        if (entity.getId() == null) {
            create(entity);
        } else {
            entity = update(entity);
        }
        return entity;
    }

    @Override
    public Collection<E> saveOrUpdate(Collection<E> entitySet) {
        Collection<E> resultSet = new HashSet<E>();
        for (E entity : entitySet) {
            resultSet.add(saveOrUpdate(entity));
        }
        return resultSet;
    }

    public void setEntityManager(EntityManager entityManager) {
        AbstractRepository.entityManager = entityManager;
    }

    @Override
    public E update(E entity) {
        EntityTransaction transac = entityManager.getTransaction();
        transac.begin();
        try {
            E result = entityManager.merge(entity);
            transac.commit();
            return result;
        } catch (Exception e) {
            rollback(transac);
            throw e;
        }
    }

    @Override
    public long count() {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT count(o) FROM " + getTableName() + " o");
        long count = (long) q.getSingleResult();
        entityManager.getTransaction().commit();
        return count;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

}
