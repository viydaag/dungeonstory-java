package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.repository.AbstractRepository;

public class FeatRepository extends AbstractRepository<Feat, Long> {

    @Override
    protected Class<Feat> getEntityClass() {
        return Feat.class;
    }

}
