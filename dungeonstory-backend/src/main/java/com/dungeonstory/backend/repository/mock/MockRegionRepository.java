package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockRegionRepository extends MockAbstractRepository<Region> {

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
}
