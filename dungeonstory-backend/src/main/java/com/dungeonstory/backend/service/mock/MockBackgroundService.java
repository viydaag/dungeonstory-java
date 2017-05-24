package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.factory.impl.BackgroundFactory;
import com.dungeonstory.backend.repository.mock.MockBackgroundRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.BackgroundDataService;

public class MockBackgroundService extends AbstractDataService<Background, Long> implements BackgroundDataService {

    private static final long serialVersionUID = -5763118490006048311L;
    
    private static MockBackgroundService instance = null;

    public static synchronized MockBackgroundService getInstance() {
        if (instance == null) {
            instance = new MockBackgroundService();
        }
        return instance;
    }

    private MockBackgroundService() {
        super();
        setEntityFactory(new BackgroundFactory());
        setRepository(new MockBackgroundRepository());
    }

}
