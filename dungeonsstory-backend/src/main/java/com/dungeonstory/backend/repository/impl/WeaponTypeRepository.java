package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.repository.AbstractRepository;

public class WeaponTypeRepository extends AbstractRepository<WeaponType, Long> {

    @Override
    protected Class<WeaponType> getEntityClass() {
        return WeaponType.class;
    }

}
