package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.factory.impl.RaceFactory;
import com.dungeonstory.backend.repository.impl.RaceRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class RaceService extends AbstractDataService<Race, Long> {

    private static RaceService instance = null;

    public static synchronized RaceService getInstance() {
        if (instance == null) {
            instance = new RaceService();
        }
        return instance;
    }

    private RaceService() {
        super();
        setEntityFactory(new RaceFactory());
        setRepository(new RaceRepository());
    }

}
