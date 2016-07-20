package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockDamageTypeRepository extends MockAbstractRepository<DamageType> {
    
    private static Long idDamageType = 1L;

    public MockDamageTypeRepository() {
        super();
    }

    @Override
    public void init() {
        List<DamageType> list = MockDataGenerator.createDamageTypes();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(DamageType entity) {
        if (entity.getId() == null) {
            entity.setId(idDamageType++);
        }
    }

}
