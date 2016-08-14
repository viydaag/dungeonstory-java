package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.repository.AbstractRepository;

public class WeaponRepository extends AbstractRepository<Weapon, Long> {

    @Override
    protected Class<Weapon> getEntityClass() {
        return Weapon.class;
    }

}
