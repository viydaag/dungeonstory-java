package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.factory.impl.WeaponTypeFactory;
import com.dungeonstory.backend.repository.mock.MockWeaponTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockWeaponTypeService extends AbstractDataService<WeaponType, Long> {

    private static MockWeaponTypeService instance = null;

    public static synchronized MockWeaponTypeService getInstance() {
        if (instance == null) {
            instance = new MockWeaponTypeService();
        }
        return instance;
    }
    
    private MockWeaponTypeService() {
        super();
        setEntityFactory(new WeaponTypeFactory());
        setRepository(new MockWeaponTypeRepository());
    }
    
    
}
