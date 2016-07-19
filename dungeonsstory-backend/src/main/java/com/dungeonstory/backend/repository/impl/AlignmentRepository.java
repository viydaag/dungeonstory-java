package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.repository.AbstractRepository;

public class AlignmentRepository extends AbstractRepository<Alignment, Long> {

    @Override
    protected Class<? extends Alignment> getEntityClass() {
        return Alignment.class;
    }

}
