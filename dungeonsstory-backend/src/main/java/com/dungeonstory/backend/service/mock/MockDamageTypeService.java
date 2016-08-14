package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.factory.impl.DamageTypeFactory;
import com.dungeonstory.backend.repository.mock.MockDamageTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockDamageTypeService extends AbstractDataService<DamageType, Long> {

	private static final long serialVersionUID = 8135097045919947666L;
	
	private static MockDamageTypeService instance = null;

    public static synchronized MockDamageTypeService getInstance() {
        if (instance == null) {
            instance = new MockDamageTypeService();
        }
        return instance;
    }

    private MockDamageTypeService() {
        super();
        setEntityFactory(new DamageTypeFactory());
        setRepository(new MockDamageTypeRepository());
    }

}
