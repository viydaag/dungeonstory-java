package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockClassRepository extends MockAbstractRepository<DSClass> {
    
    private static Long idClass = 1L;

    public MockClassRepository() {
        super();
    }

    @Override
    public void init() {
        List<DSClass> list = MockDataGenerator.createClasses();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(DSClass entity) {
        if (entity.getId() == null) {
            entity.setId(idClass++);
        }
    }

}
