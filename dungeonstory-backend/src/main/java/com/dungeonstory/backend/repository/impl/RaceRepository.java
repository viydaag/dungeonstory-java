package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.repository.AbstractRepository;

public class RaceRepository extends AbstractRepository<Race, Long> {

    private static final long serialVersionUID = 8455243116017898374L;

    @Override
    protected Class<Race> getEntityClass() {
        return Race.class;
    }

}
