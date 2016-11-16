package com.dungeonstory.backend.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Repository<E extends Entity, K extends Serializable> {

    /**
    *
    * @param entity
    *            the entity to create
     * @return 
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
    *            the prmiary key of the entity to delete
    */
    void delete(K key);

    /**
    * 
    * @param entitySet
    */
    void delete(Collection<E> entitySet);

    /**
    *
    * @return the entity list found
    */
    List<E> findAll();

	/**
    *
    * @return the entity sorted list found
    */
    List<E> findAllOrderBy(String column, String order);
    
    /**
    *
    * @return the entity list found
    */
    List<E> findAllBy(String column, String value);
    
    /**
    *
    * @return the entity list found
    */
    List<E> findAllPaged(int firstRow, int pageSize);

    /**
    *
    * @return the entity list found
    */
    List<E> findAllPagedOrderBy(int firstRow, int pageSize, String orderColumn, String order);

    /**
    *
    * @return the entity list found
    */
    List<E> findAllByLike(String column, String value);

    List<E> findAllByLikePaged(String column, String value, int firstRow, int pageSize);

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
    *
    * @param entity
    *            the entity to refresh
    */
    void refresh(E entity);

    /**
    * Creates the object in the database if the ID is null. Updates the object
    * in the database if the ID is not null.
    *
    * @param entity
    * @return the object after the action.
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
    * @param entity
    *            the entity to update
    * @return the entity updated
    */
    E update(E entity);

    /**
     * 
     * @return the entity count
     */
    long count();

}
