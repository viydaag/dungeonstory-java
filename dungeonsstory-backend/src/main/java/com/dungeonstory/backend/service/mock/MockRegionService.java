package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.factory.impl.RegionFactory;
import com.dungeonstory.backend.repository.mock.MockRegionRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockRegionService extends AbstractDataService<Region, Long> {

    public MockRegionService() {
        super();
        setEntityFactory(new RegionFactory());
        setRepository(new MockRegionRepository());
    }
    
    
}
