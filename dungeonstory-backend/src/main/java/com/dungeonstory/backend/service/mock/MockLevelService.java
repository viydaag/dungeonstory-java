package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.repository.mock.MockLevelRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.LevelDataService;

public class MockLevelService extends AbstractDataService<Level, Long> implements LevelDataService {

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
        setEntityFactory(() -> new Level());
        setRepository(new MockLevelRepository());
    }

    @Override
    public Level getNextLevel(Level level) {
        // TODO Auto-generated method stub
        return null;
    }

}
