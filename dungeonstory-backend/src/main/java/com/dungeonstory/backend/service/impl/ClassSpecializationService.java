package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.repository.impl.ClassSpecializationRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ClassSpecializationDataService;

public class ClassSpecializationService extends AbstractDataService<ClassSpecialization, Long>
        implements ClassSpecializationDataService {

    private static final long serialVersionUID = 8366455895761725369L;

    private static ClassSpecializationService instance = null;
    private ClassSpecializationRepository     repository = null;

    public static synchronized ClassSpecializationService getInstance() {
        if (instance == null) {
            instance = new ClassSpecializationService();
        }
        return instance;
    }

    private ClassSpecializationService() {
        super();
        setEntityFactory(() -> new ClassSpecialization());
        repository = new ClassSpecializationRepository();
        setRepository(repository);
    }

    @Override
    public List<ClassSpecialization> findAllDivineDomainSpecializations() {
        return repository.findAllDivineDomainSpecializations();
    }

}
