package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockClassFeatureRepository extends MockAbstractRepository<ClassFeature> {

    private static Long idCF = 1L;

    public MockClassFeatureRepository() {
        super();
    }

    @Override
    public void init() {
        List<ClassFeature> list = MockDataGenerator.createClassFeatures();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(ClassFeature entity) {
        if (entity.getId() == null) {
            entity.setId(idCF++);
        }
    }

}
