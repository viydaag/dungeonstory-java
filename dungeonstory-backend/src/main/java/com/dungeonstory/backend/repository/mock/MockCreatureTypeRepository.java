package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockCreatureTypeRepository extends MockAbstractRepository<CreatureType> {

    private static Long idCreatureType = 1L;

    public MockCreatureTypeRepository() {
        super();
    }

    @Override
    public void init() {
        List<CreatureType> list = MockDataGenerator.createCreatureTypes();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(CreatureType entity) {
        if (entity.getId() == null) {
            entity.setId(idCreatureType++);
        }
    }

}
