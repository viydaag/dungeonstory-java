package com.dungeonstory.backend.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.dungeonstory.backend.factory.Factory;
import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.repository.Repository;

public interface DataService<E extends Entity, K extends Serializable> {

    /**
     * 
     * @return the entity created
     */
    E create();

    /**
     * 
     * @param entity
     *            the entity to create
     */
    void create(E entity);

    /**
     *
     * @param entity
     *            the entity to delete
     */
    void delete(E entity);

    /**
     *
     * @param key
     *            the key of the entity to delete
     */
    void delete(K key);

    /**
     * 
     * @param entitySet
     */
    void delete(Collection<E> entitySet);

    /**
     *
     * @return all instances of the entity
     */
    Collection<E> findAll();

    /**
    *
    * @return all instances of the entity sorted
    */
    Collection<E> findAllOrderBy(String column, String order);
    
    /**
    *
    * @return the entity list found
    */
    Collection<E> findAllBy(String column, String value);
    
    /**
    *
    * @return the entity list found
    */
    Collection<E> findAllByLike(String column, String value);

    List<E> findAllPaged(int firstRow, int pageSize);

    List<E> findAllByLikePaged(String column, String value, int firstRow, int pageSize);

    List<E> findAllPagedOrderBy(int firstRow, int pageSize, String orderColumn, String order);

    List<E> findAllByLikePagedOrderBy(String column, String value, int firstRow, int pageSize, String orderColumn,
            String order);

    /**
     *
     * @param key
     *            the key of the entity to read
     * @return the entity read
     */
    E read(K key);

    /**
     * Refresh the state of the instance from the database, 
     * overwriting changes made to the entity, if any. 
     * @param entity
     */
    void refresh(E entity);

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    E saveOrUpdate(E entity);

    /**
     * 
     * @param entitySet
     * @return
     */
    Collection<E> saveOrUpdate(Collection<E> entitySet);

    /**
     *
     * @param dao
     *            the DAO to set
     */
    void setRepository(Repository<E, K> dao);

    /**
     *
     * @param factory
     *            the factory to set
     */
    void setEntityFactory(Factory<E> factory);

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    E update(E entity);

    /**
     * 
     * @return the entity count with given filter
     */
    int countWithFilter(String column, String value);

    /**
     * 
     * @return the entity count
     */
    long count();
}
