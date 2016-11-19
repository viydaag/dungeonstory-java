package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ClassSpecializationRepository extends AbstractRepository<ClassSpecialization, Long> {

    private static final long serialVersionUID = 4560405765272948577L;

    @Override
    protected Class<ClassSpecialization> getEntityClass() {
        return ClassSpecialization.class;
    }
    
}
