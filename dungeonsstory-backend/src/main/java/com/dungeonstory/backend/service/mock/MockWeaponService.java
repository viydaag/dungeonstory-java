package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.factory.impl.WeaponFactory;
import com.dungeonstory.backend.repository.mock.MockWeaponRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockWeaponService extends AbstractDataService<Weapon, Long> {

    private static MockWeaponService instance = null;

    public static synchronized MockWeaponService getInstance() {
        if (instance == null) {
            instance = new MockWeaponService();
        }
        return instance;
    }

    private MockWeaponService() {
        super();
        setEntityFactory(new WeaponFactory());
        setRepository(new MockWeaponRepository());
    }

}
