package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ArmorTypeRepository extends AbstractRepository<ArmorType, Long> {

    @Override
    protected Class<ArmorType> getEntityClass() {
        return ArmorType.class;
    }

}
