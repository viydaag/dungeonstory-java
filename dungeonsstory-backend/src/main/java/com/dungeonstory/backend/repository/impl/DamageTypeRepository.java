package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.repository.AbstractRepository;

public class DamageTypeRepository extends AbstractRepository<DamageType, Long> {

    private static final long serialVersionUID = 7079452197075187756L;

    @Override
    protected Class<DamageType> getEntityClass() {
        return DamageType.class;
    }

}
