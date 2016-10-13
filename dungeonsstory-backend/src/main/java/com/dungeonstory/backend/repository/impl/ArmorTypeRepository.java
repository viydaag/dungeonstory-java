package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ArmorTypeRepository extends AbstractRepository<ArmorType, Long> {

    private static final long serialVersionUID = -1820352807995290024L;

    @Override
    protected Class<ArmorType> getEntityClass() {
        return ArmorType.class;
    }

}
