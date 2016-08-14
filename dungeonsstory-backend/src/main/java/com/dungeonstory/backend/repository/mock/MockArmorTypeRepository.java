package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockArmorTypeRepository extends MockAbstractRepository<ArmorType> {

    private static Long idArmorType = 1L;

    public MockArmorTypeRepository() {
        super();
    }

    @Override
    public void init() {
        List<ArmorType> list = MockDataGenerator.createArmorTypes();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(ArmorType entity) {
        if (entity.getId() == null) {
            entity.setId(idArmorType++);
        }
    }

}
