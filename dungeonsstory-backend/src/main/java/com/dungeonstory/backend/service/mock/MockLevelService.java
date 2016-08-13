package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.factory.impl.LevelFactory;
import com.dungeonstory.backend.repository.mock.MockLevelRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockLevelService extends AbstractDataService<Level, Long> {

	private static final long serialVersionUID = 3986892209956221917L;
	
	private static MockLevelService instance = null;

    public static synchronized MockLevelService getInstance() {
        if (instance == null) {
            instance = new MockLevelService();
        }
        return instance;
    }

    private MockLevelService() {
        super();
        setEntityFactory(new LevelFactory());
        setRepository(new MockLevelRepository());
    }

}
