package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.repository.mock.MockRegionRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.RegionDataService;

public class MockRegionService extends AbstractDataService<Region, Long> implements RegionDataService {

    private static final long serialVersionUID = -64652677610198619L;

    private static MockRegionService instance = null;

    public static synchronized MockRegionService getInstance() {
        if (instance == null) {
            instance = new MockRegionService();
        }
        return instance;
    }

    private MockRegionService() {
        super();
        setEntityFactory(() -> new Region());
        setRepository(new MockRegionRepository());
    }

}
