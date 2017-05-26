package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.repository.mock.MockWeaponTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.WeaponTypeDataService;

public class MockWeaponTypeService extends AbstractDataService<WeaponType, Long> implements WeaponTypeDataService {

    private static final long serialVersionUID = 5408240455537708490L;

    private static MockWeaponTypeService instance = null;

    public static synchronized MockWeaponTypeService getInstance() {
        if (instance == null) {
            instance = new MockWeaponTypeService();
        }
        return instance;
    }

    private MockWeaponTypeService() {
        super();
        setEntityFactory(() -> new WeaponType());
        setRepository(new MockWeaponTypeRepository());
    }

}
