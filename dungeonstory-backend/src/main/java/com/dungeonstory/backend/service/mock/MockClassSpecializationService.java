package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.repository.mock.MockClassSpecializationRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ClassSpecializationDataService;

public class MockClassSpecializationService extends AbstractDataService<ClassSpecialization, Long> implements ClassSpecializationDataService {

    private static final long serialVersionUID = 6783042322281503945L;
    
    private static MockClassSpecializationService instance = null;

    public static synchronized MockClassSpecializationService getInstance() {
        if (instance == null) {
            instance = new MockClassSpecializationService();
        }
        return instance;
    }

    private MockClassSpecializationService() {
        super();
        setEntityFactory(() -> new ClassSpecialization());
        setRepository(new MockClassSpecializationRepository());
    }

}
