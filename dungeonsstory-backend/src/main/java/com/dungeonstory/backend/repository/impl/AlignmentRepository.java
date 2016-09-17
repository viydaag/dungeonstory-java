package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.repository.AbstractRepository;

public class AlignmentRepository extends AbstractRepository<Alignment, Long> {

    private static final long serialVersionUID = 5570800252737580747L;

    @Override
    protected Class<Alignment> getEntityClass() {
        return Alignment.class;
    }

}
