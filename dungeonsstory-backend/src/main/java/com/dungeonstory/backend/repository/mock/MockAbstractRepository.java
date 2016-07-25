package com.dungeonstory.backend.repository.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.dungeonstory.backend.repository.Entity;
import com.dungeonstory.backend.repository.Repository;

public abstract class MockAbstractRepository<E extends Entity> implements Repository<E, Long> {
    
    protected Map<Long, E> entities = new HashMap<Long, E>();
    
    public MockAbstractRepository() {
        init();
    }
    
    public abstract void init();
    
    /**
     * This method is a manual replacement for database id generation.
     * @param entity
     */
    public abstract void setId(E entity);

    @Override
    public void create(E entity) {
    	setId(entity);
        entities.put(entity.getId(), entity);
    }

    @Override
    public void delete(E entity) {
        entities.remove(entity.getId());        
    }

    @Override
    public void delete(Long key) {
        entities.remove(key);
    }

    @Override
    public void delete(Collection<E> entitySet) {
        for (E entity : entitySet) {
            delete(entity);
        }
        
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<E>(entities.values());
    }

    @Override
    public E read(Long key) {
        return entities.get(key);
    }

    @Override
    public void refresh(E entity) {
        // TODO Auto-generated method stub
        
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
        Collection<E> result = new HashSet<E>();
        for (E entity : entitySet) {
            E region = saveOrUpdate(entity);
            if (result.contains(region)) {
                result.remove(region);
            }
            result.add(region);
        }
        return result;
    }

    @Override
    public E update(E entity) {
        if (entities.containsValue(entity)) {
            entities.put(entity.getId(), entity);
            return entity;
        }
        
        throw new IllegalArgumentException("No entity with id " + entity.getId() + " found");
    }

}