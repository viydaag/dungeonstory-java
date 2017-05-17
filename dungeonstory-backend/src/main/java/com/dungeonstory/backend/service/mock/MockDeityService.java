package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.factory.impl.DeityFactory;
import com.dungeonstory.backend.repository.mock.MockDeityRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockDeityService extends AbstractDataService<Deity, Long> {

    private static final long serialVersionUID = 3713358506919598497L;

    private static MockDeityService instance = null;

    public static synchronized MockDeityService getInstance() {
        if (instance == null) {
            instance = new MockDeityService();
        }
        return instance;
    }

    private MockDeityService() {
        super();
        setEntityFactory(new DeityFactory());
        setRepository(new MockDeityRepository());
    }

}
