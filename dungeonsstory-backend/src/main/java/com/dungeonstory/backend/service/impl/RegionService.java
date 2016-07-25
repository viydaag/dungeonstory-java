package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.factory.impl.RegionFactory;
import com.dungeonstory.backend.repository.impl.RegionRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class RegionService extends AbstractDataService<Region, Long> {

    private static RegionService instance = null;

    public static synchronized RegionService getInstance() {
        if (instance == null) {
            instance = new RegionService();
        }
        return instance;
    }

    private RegionService() {
        super();
        setEntityFactory(new RegionFactory());
        setRepository(new RegionRepository());
    }

}
