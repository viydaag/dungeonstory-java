package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.repository.AbstractRepository;

public class DeityRepository extends AbstractRepository<Deity, Long> {

    @Override
    protected Class<Deity> getEntityClass() {
        return Deity.class;
    }

}
