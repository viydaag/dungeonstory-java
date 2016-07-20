package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.WeaponType;

public class MockWeaponTypeRepository extends MockAbstractRepository<WeaponType> {
    
    private static Long idWeaponType = 1L;

    public MockWeaponTypeRepository() {
        super();
    }

    @Override
    public void init() {
        //TODO
//        List<WeaponType> list = MockDataGenerator.createAbilities();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(WeaponType entity) {
        if (entity.getId() == null) {
            entity.setId(idWeaponType++);
        }
    }

}
