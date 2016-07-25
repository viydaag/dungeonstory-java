package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.repository.AbstractRepository;

public class RaceRepository extends AbstractRepository<Race, Long> {

    @Override
    protected Class<Race> getEntityClass() {
        return Race.class;
    }

}
