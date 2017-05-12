package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.factory.impl.AdventureFactory;
import com.dungeonstory.backend.repository.mock.MockAdventureRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockAdventureService extends AbstractDataService<Adventure, Long> {

    private static final long serialVersionUID = 3713358506919598497L;

    private static MockAdventureService instance = null;

    public static synchronized MockAdventureService getInstance() {
        if (instance == null) {
            instance = new MockAdventureService();
        }
        return instance;
    }

    private MockAdventureService() {
        super();
        setEntityFactory(new AdventureFactory());
        setRepository(new MockAdventureRepository());
    }

}
