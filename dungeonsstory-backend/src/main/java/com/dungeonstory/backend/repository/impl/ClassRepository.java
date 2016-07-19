package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ClassRepository extends AbstractRepository<DSClass, Long> {

    @Override
    protected Class<? extends DSClass> getEntityClass() {
        return DSClass.class;
    }

}
