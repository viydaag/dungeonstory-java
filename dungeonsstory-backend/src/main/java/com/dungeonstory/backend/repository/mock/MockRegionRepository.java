package com.dungeonstory.backend.repository.mock;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.mock.MockDataGenerator;
import com.dungeonstory.backend.repository.Repository;

public class MockRegionRepository implements Repository<Region, Long> {
    
    private static Map<Long, Region> regions;
    
    private static Long idRegion = 1L;

    public MockRegionRepository() {
        List<Region> list = MockDataGenerator.createRegions();
        
        regions = list.stream().collect(Collectors.toMap(x -> idRegion++, item -> item));
        
    }

    @Override
    public void create(Region entity) {
        entity.setId(idRegion);
        regions.put(idRegion, entity);
        idRegion++;
    }

    @Override
    public void delete(Region entity) {
        regions.remove(entity.getId());        
    }

    @Override
    public void delete(Long key) {
        regions.remove(key);
    }

    @Override
    public void delete(Collection<Region> entitySet) {
        for (Region entity : entitySet) {
            delete(entity);
        }
        
    }

    @Override
    public Collection<Region> findAll() {
        return regions.values();
    }

    @Override
    public Region read(Long key) {
        return regions.get(key);
    }

    @Override
    public void refresh(Region entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Region saveOrUpdate(Region entity) {
        if (entity.getId() == null) {
            create(entity);
        } else {
            entity = update(entity);
        }
        return entity;
    }

    @Override
    public Collection<Region> saveOrUpdate(Collection<Region> entitySet) {
        Collection<Region> result = new HashSet<Region>();
        for (Region entity : entitySet) {
            Region region = saveOrUpdate(entity);
            if (result.contains(region)) {
                result.remove(region);
            }
            result.add(region);
        }
        return result;
    }

    @Override
    public Region update(Region entity) {
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(entity.getId()) != null) {
                regions.put(entity.getId(), entity);
                return entity;
            }
        }
        
        throw new IllegalArgumentException("No product with id " + entity.getId() + " found");
    }

}
