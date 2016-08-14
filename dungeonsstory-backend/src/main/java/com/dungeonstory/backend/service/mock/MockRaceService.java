package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.factory.impl.RaceFactory;
import com.dungeonstory.backend.repository.mock.MockRaceRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockRaceService extends AbstractDataService<Race, Long> {

	private static final long serialVersionUID = 7176060836514456145L;
	
	private static MockRaceService instance = null;

    public static synchronized MockRaceService getInstance() {
        if (instance == null) {
            instance = new MockRaceService();
        }
        return instance;
    }

    private MockRaceService() {
        super();
        setEntityFactory(new RaceFactory());
        setRepository(new MockRaceRepository());
    }

}
