package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.repository.AbstractRepository;

public class CreatureTypeRepository extends AbstractRepository<CreatureType, Long> {

    private static final long serialVersionUID = -6819174153720441506L;

    @Override
    protected Class<CreatureType> getEntityClass() {
        return CreatureType.class;
    }

}
