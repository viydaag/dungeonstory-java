package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockRegionRepository extends MockAbstractRepository<Region> {

    //    private static Map<Long, Region> entities = new HashMap<Long, Region>();

    private static Long idRegion = 1L;

    public MockRegionRepository() {
        super();
    }

    @Override
    public void init() {
        List<Region> list = MockDataGenerator.createRegions();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Region entity) {
        if (entity.getId() == null) {
            entity.setId(idRegion++);
        }
    }

    //    @Override
    //    public void create(Region entity) {
    //        entity.setId(idRegion);
    //        entities.put(idRegion, entity);
    //        idRegion++;
    //    }
    //
    //    @Override
    //    public void delete(Region entity) {
    //        entities.remove(entity.getId());        
    //    }
    //
    //    @Override
    //    public void delete(Long key) {
    //        entities.remove(key);
    //    }
    //
    //    @Override
    //    public void delete(Collection<Region> entitySet) {
    //        for (Region entity : entitySet) {
    //            delete(entity);
    //        }
    //        
    //    }
    //
    //    @Override
    //    public Collection<Region> findAll() {
    //        return entities.values();
    //    }
    //
    //    @Override
    //    public Region read(Long key) {
    //        return entities.get(key);
    //    }
    //
    //    @Override
    //    public void refresh(Region entity) {
    //        // TODO Auto-generated method stub
    //        
    //    }
    //
    //    @Override
    //    public Region saveOrUpdate(Region entity) {
    //        if (entity.getId() == null) {
    //            create(entity);
    //        } else {
    //            entity = update(entity);
    //        }
    //        return entity;
    //    }
    //
    //    @Override
    //    public Collection<Region> saveOrUpdate(Collection<Region> entitySet) {
    //        Collection<Region> result = new HashSet<Region>();
    //        for (Region entity : entitySet) {
    //            Region region = saveOrUpdate(entity);
    //            if (result.contains(region)) {
    //                result.remove(region);
    //            }
    //            result.add(region);
    //        }
    //        return result;
    //    }
    //
    //    @Override
    //    public Region update(Region entity) {
    //        if (entities.containsValue(entity)) {
    //            entities.put(entity.getId(), entity);
    //            return entity;
    //        }
    //        
    //        throw new IllegalArgumentException("No product with id " + entity.getId() + " found");
    //    }

}
