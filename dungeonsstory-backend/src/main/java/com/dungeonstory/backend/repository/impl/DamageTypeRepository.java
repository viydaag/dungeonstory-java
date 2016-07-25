package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.repository.AbstractRepository;

public class DamageTypeRepository extends AbstractRepository<DamageType, Long> {

    @Override
    protected Class<DamageType> getEntityClass() {
        return DamageType.class;
    }

}
