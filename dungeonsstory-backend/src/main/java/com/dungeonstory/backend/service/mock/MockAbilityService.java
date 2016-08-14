package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.factory.impl.AbilityFactory;
import com.dungeonstory.backend.repository.mock.MockAbilityRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockAbilityService extends AbstractDataService<Ability, Long> {

	private static final long serialVersionUID = 3713358506919598497L;
	
	private static MockAbilityService instance = null;

    public static synchronized MockAbilityService getInstance() {
        if (instance == null) {
            instance = new MockAbilityService();
        }
        return instance;
    }

    private MockAbilityService() {
        super();
        setEntityFactory(new AbilityFactory());
        setRepository(new MockAbilityRepository());
    }

}
