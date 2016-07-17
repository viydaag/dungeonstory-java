package com.dungeonstory.backend.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class AbstractRepository<E extends Entity, K extends Serializable> implements Repository<E, K> {

	
	private static final String         PERSISTENCE_UNIT_HSQL_NAME  = "dungeonstory-hsql2";
    private static final String         PERSISTENCE_UNIT_MYSQL_NAME = "dungeonstory-mysql";
	
	protected static EntityManagerFactory factory;
//    @PersistenceContext(unitName="dungeonstory-hsql")
    protected static EntityManager entityManager;

    public AbstractRepository() {
        super();
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_HSQL_NAME);
        entityManager = factory.createEntityManager();
    }

    @Override
    public synchronized void create(E entity) {
    	EntityTransaction transac = entityManager.getTransaction();
        transac.begin();
        entityManager.persist(entity);
        flushAndCloseEntityManager();
        transac.commit();
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
        entityManager.remove(entity);
        flushAndCloseEntityManager();
        transac.commit();
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

        Query query = entityManager.createQuery("DELETE FROM " + getEntityClass().getName() + " o WHERE o.id = :id");
        query.setParameter("id", key);
        query.executeUpdate();
    }

    @Override
    public synchronized void delete(Collection<E> entitySet) {
        for (E entity : entitySet) {
            delete(entity);
        }
    }

    @Override
    public List<E> findAll() {
        Query query = entityManager.createQuery("SELECT o FROM " + getEntityClass().getName() + " o");
        return query.getResultList();
    }

    protected abstract Class<? extends E> getEntityClass();

    @Override
    public E read(K key) {
        E result = (E) entityManager.find(getEntityClass(), key);
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
        E result = entityManager.merge(entity);
        flushAndCloseEntityManager();
        transac.commit();
        return result;
    }

    /**
     * Due to memory leaks, the entity manager has to be flushed and closed after
     * each db operation. All the elements retrieved while the db access keep
     * a reference to the entity manager and can never be garbaged.
     * By flushing and closing the entity manager, these objects can be free.
     */
    private void flushAndCloseEntityManager(){
//        entityManager.flush();
//        entityManager.close();
    }

}
