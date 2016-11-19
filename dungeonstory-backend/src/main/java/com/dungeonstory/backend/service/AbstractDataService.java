package com.dungeonstory.backend.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.dungeonstory.backend.factory.Factory;
import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.repository.Repository;

public abstract class AbstractDataService<E extends Entity, K extends Serializable> implements DataService<E, K>, Serializable {

    private static final long serialVersionUID = 4655778098973992981L;
    
    protected Repository<E, K> entityRepository;
    protected Factory<E> entityFactory;

    public AbstractDataService() {
        super();
    }

    /**
     *
     * @return the entity created
     */
    @Override
    public E create() {
        return entityFactory.create();
    }

    /**
     *
     * @param entity
     *            the entity to create
     */
    @Override
    public void create(E entity) {
        entityRepository.create(entity);
    }

    /**
     *
     * @param entity
     *            the entity to delete
     */
    @Override
    public void delete(E entity) {
        entityRepository.delete(entity);
    }

    @Override
    public void delete(K key) {
        entityRepository.delete(key);
    }

    @Override
    public void delete(Collection<E> entitySet) {
        entityRepository.delete(entitySet);
    }
    
    @Override
    public void refresh(E entity) {
        entityRepository.refresh(entity);
    }

    @Override
    public List<E> findAll() {
        return entityRepository.findAll();
    }

    @Override
    public List<E> findAllOrderBy(String[] column, String[] order) {
        return entityRepository.findAllOrderBy(column, order);
    }
    
    @Override
    public List<E> findAllOrderBy(String column, String order) {
        return entityRepository.findAllOrderBy(new String[] { column }, new String[] { order });
    }

    @Override
    public List<E> findAllBy(String column, String value) {
        if (StringUtils.isEmpty(column) || StringUtils.isEmpty(value)) {
            return findAll();
        }
        return entityRepository.findAllBy(column, value);
    }
    
    @Override
    public List<E> findAllByLike(String column, String value) {
        if (StringUtils.isEmpty(column) || StringUtils.isEmpty(value)) {
            return findAll();
        } 
        return entityRepository.findAllByLike(column, value);
    }

    @Override
    public List<E> findAllPaged(int firstRow, int pageSize) {
        return entityRepository.findAllPaged(firstRow, pageSize);
    }

    @Override
    public List<E> findAllPagedOrderBy(int firstRow, int pageSize, String[] orderColumn, String[] order) {
        return entityRepository.findAllPagedOrderBy(firstRow, pageSize, orderColumn, order);
    }

    @Override
    public List<E> findAllByLikePaged(String column, String value, int firstRow, int pageSize) {
        if (StringUtils.isEmpty(column) || StringUtils.isEmpty(value)) {
            return findAllPaged(firstRow, pageSize);
        }
        return entityRepository.findAllByLikePaged(column, value, firstRow, pageSize);
    }

    @Override
    public List<E> findAllByLikePagedOrderBy(String column, String value, int firstRow, int pageSize,
            String[] orderColumn, String[] order) {
        if (StringUtils.isEmpty(column) || StringUtils.isEmpty(value)) {
            if (ArrayUtils.isEmpty(orderColumn) || ArrayUtils.isEmpty(order)) {
                return findAllPaged(firstRow, pageSize);
            }
            return findAllPagedOrderBy(firstRow, pageSize, orderColumn, order);
        }
        if (ArrayUtils.isEmpty(orderColumn) || ArrayUtils.isEmpty(order)) {
            return findAllByLikePaged(column, value, firstRow, pageSize);
        }
        return entityRepository.findAllByLikePagedOrderBy(column, value, firstRow, pageSize, orderColumn, order);
    }

    /**
     *
     * @param key
     *            the key of the entity to read
     * @return the entity read
     */
    @Override
    public E read(K key) {
        return entityRepository.read(key);
    }

    /**
     * 
     * @param entity
     * @return
     */
    @Override
    public E saveOrUpdate(E entity) {
        return entityRepository.saveOrUpdate(entity);
    }

    /**
     * 
     * @param entitySet
     * @return
     */
    @Override
    public Collection<E> saveOrUpdate(Collection<E> entitySet) {
        return entityRepository.saveOrUpdate(entitySet);
    }

    /**
     *
     * @param repository
     *            the entity repository to set
     */
    @Override
    public void setRepository(Repository<E, K> repository) {
        this.entityRepository = repository;
    }

    /**
     *
     * @param entityFactory
     *            the entity factory to set
     */
    @Override
    public void setEntityFactory(Factory<E> entityFactory) {
        this.entityFactory = entityFactory;
    }

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    @Override
    public E update(E entity) {
        return entityRepository.update(entity);
    }

    @Override
    public long count() {
        return entityRepository.count();
    }

    /**
     * 
     * @return the entity count with given filter
     */
    @Override
    public int countWithFilter(String column, String value) {
        return findAllByLike(column, value).size();
    }

}
