package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.factory.impl.ClassSpecializationFactory;
import com.dungeonstory.backend.repository.impl.ClassSpecializationRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class ClassSpecializationService extends AbstractDataService<ClassSpecialization, Long> {
	
    private static final long serialVersionUID = 8366455895761725369L;

    private static ClassSpecializationService instance = null;

    public static synchronized ClassSpecializationService getInstance() {
        if (instance == null) {
            instance = new ClassSpecializationService();
        }
        return instance;
    }

    private ClassSpecializationService() {
        super();
        setEntityFactory(new ClassSpecializationFactory());
        setRepository(new ClassSpecializationRepository());
    }

}
