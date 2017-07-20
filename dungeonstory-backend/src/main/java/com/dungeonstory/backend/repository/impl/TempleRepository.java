package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Temple;
import com.dungeonstory.backend.repository.AbstractRepository;

public class TempleRepository extends AbstractRepository<Temple, Long> {

    private static final long serialVersionUID = -6813449800000248213L;

    @Override
    protected Class<Temple> getEntityClass() {
        return Temple.class;
    }

}
