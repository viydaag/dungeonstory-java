package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockWeaponTypeRepository extends MockAbstractRepository<WeaponType> {

    private static Long idWeaponType = 1L;

    public MockWeaponTypeRepository() {
        super();
    }

    @Override
    public void init() {
        List<WeaponType> list = MockDataGenerator.createWeaponTypes();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(WeaponType entity) {
        if (entity.getId() == null) {
            entity.setId(idWeaponType++);
        }
    }

}
