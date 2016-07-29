package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.Weapon;

public class MockWeaponRepository extends MockAbstractRepository<Weapon> {

    private static Long idWeapon = 1L;

    public MockWeaponRepository() {
        super();
    }

    @Override
    public void init() {
//        List<Weapon> list = MockDataGenerator.createAbilities();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Weapon entity) {
        if (entity.getId() == null) {
            entity.setId(idWeapon++);
        }
    }

}
