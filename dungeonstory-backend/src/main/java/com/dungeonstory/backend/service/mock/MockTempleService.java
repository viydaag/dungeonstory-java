package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Temple;
import com.dungeonstory.backend.repository.mock.MockTempleRepository;
import com.dungeonstory.backend.service.TempleDataService;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockTempleService extends AbstractDataService<Temple, Long> implements TempleDataService {

    private static final long serialVersionUID = 5121055955177298163L;
    
    private static MockTempleService instance = null;

    public static synchronized MockTempleService getInstance() {
        if (instance == null) {
            instance = new MockTempleService();
        }
        return instance;
    }

    private MockTempleService() {
        super();
        setEntityFactory(() -> new Temple());
        setRepository(new MockTempleRepository());
    }

}
