package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.repository.AbstractRepository;

public class DivineDomainRepository extends AbstractRepository<DivineDomain, Long> {

    @Override
    protected Class<DivineDomain> getEntityClass() {
        return DivineDomain.class;
    }

}
