package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.repository.impl.RaceRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.RaceDataService;

public class RaceService extends AbstractDataService<Race, Long> implements RaceDataService {

    private static final long serialVersionUID = -5279870991334827589L;

    private static RaceService instance = null;

    public static synchronized RaceService getInstance() {
        if (instance == null) {
            instance = new RaceService();
        }
        return instance;
    }

    private RaceService() {
        super();
        setEntityFactory(() -> new Race());
        setRepository(new RaceRepository());
    }

}
