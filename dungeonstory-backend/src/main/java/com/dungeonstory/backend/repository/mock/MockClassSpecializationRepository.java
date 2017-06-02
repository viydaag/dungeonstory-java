package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockClassSpecializationRepository extends MockAbstractRepository<ClassSpecialization> {

    private static Long idClassSpec = 1L;

    public MockClassSpecializationRepository() {
        super();
    }

    @Override
    public void init() {
        List<ClassSpecialization> list = MockDataGenerator.createClassSpecializations();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(ClassSpecialization entity) {
        if (entity.getId() == null) {
            entity.setId(idClassSpec++);
        }
    }

}
