package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.repository.AbstractRepository;

public class RegionRepository extends AbstractRepository<Region, Long> {

    @Override
    protected Class<? extends Region> getEntityClass() {
        return Region.class;
    }

}
