package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.repository.AbstractRepository;

public class RegionRepository extends AbstractRepository<Region, Long> {

    private static final long serialVersionUID = 613150042929010861L;

    @Override
    protected Class<Region> getEntityClass() {
        return Region.class;
    }

}
