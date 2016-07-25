package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockAbilityRepository extends MockAbstractRepository<Ability> {
    
    private static Long idAbility = 1L;

    public MockAbilityRepository() {
        super();
    }

    @Override
    public void init() {
        List<Ability> list = MockDataGenerator.createAbilities();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Ability entity) {
        if (entity.getId() == null) {
            entity.setId(idAbility++);
        }
    }

}
