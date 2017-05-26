package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.repository.mock.MockDamageTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.DamageTypeDataService;

public class MockDamageTypeService extends AbstractDataService<DamageType, Long> implements DamageTypeDataService {

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
        setEntityFactory(() -> new DamageType());
        setRepository(new MockDamageTypeRepository());
    }

}
