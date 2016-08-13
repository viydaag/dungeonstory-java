package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.factory.impl.FeatFactory;
import com.dungeonstory.backend.repository.mock.MockFeatRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockFeatService extends AbstractDataService<Feat, Long> {

	private static final long serialVersionUID = 2149292729288574964L;
	
	private static MockFeatService instance = null;

    public static synchronized MockFeatService getInstance() {
        if (instance == null) {
            instance = new MockFeatService();
        }
        return instance;
    }

    private MockFeatService() {
        super();
        setEntityFactory(new FeatFactory());
        setRepository(new MockFeatRepository());
    }

}
