package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.factory.impl.RegionFactory;
import com.dungeonstory.backend.repository.mock.MockRegionRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockRegionService extends AbstractDataService<Region, Long> {

    private static MockRegionService instance = null;

    public static synchronized MockRegionService getInstance() {
        if (instance == null) {
            instance = new MockRegionService();
        }
        return instance;
    }
    
    private MockRegionService() {
        super();
        setEntityFactory(new RegionFactory());
        setRepository(new MockRegionRepository());
    }
    
    
}
