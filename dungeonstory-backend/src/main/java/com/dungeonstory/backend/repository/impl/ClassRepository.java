package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ClassRepository extends AbstractRepository<DSClass, Long> {

    private static final long serialVersionUID = -7753323656396612312L;

    @Override
    protected Class<DSClass> getEntityClass() {
        return DSClass.class;
    }
    
}
