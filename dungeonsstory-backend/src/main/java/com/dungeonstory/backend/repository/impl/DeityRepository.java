package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.repository.AbstractRepository;

public class DeityRepository extends AbstractRepository<Deity, Long> {

    private static final long serialVersionUID = -6748031609537610763L;

    @Override
    protected Class<Deity> getEntityClass() {
        return Deity.class;
    }

}
