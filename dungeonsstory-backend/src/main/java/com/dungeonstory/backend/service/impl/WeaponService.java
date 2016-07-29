package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.factory.impl.WeaponFactory;
import com.dungeonstory.backend.repository.impl.WeaponRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class WeaponService extends AbstractDataService<Weapon, Long> {

    private static WeaponService instance = null;

    public static synchronized WeaponService getInstance() {
        if (instance == null) {
            instance = new WeaponService();
        }
        return instance;
    }

    private WeaponService() {
        super();
        setEntityFactory(new WeaponFactory());
        setRepository(new WeaponRepository());
    }

}
