package com.dungeonstory.backend.service.mock;

import java.util.List;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.factory.impl.FeatFactory;
import com.dungeonstory.backend.repository.mock.MockFeatRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.FeatDataService;

public class MockFeatService extends AbstractDataService<Feat, Long> implements FeatDataService {

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

    @Override
    public List<Feat> findAllFeats() {
        return ((MockFeatRepository) entityRepository).findAllFeats();
    }

    @Override
    public List<Feat> findAllClassFeatures() {
        return ((MockFeatRepository) entityRepository).findAllClassFeatures();
    }

}
