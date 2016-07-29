package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.Armor;

public class MockArmorRepository extends MockAbstractRepository<Armor> {

    private static Long idArmor = 1L;

    public MockArmorRepository() {
        super();
    }

    @Override
    public void init() {
//        List<Armor> list = MockDataGenerator.createAbilities();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Armor entity) {
        if (entity.getId() == null) {
            entity.setId(idArmor++);
        }
    }

}
