package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.Spell;

public class MockSpellRepository extends MockAbstractRepository<Spell> {

    private static Long idSpell = 1L;

    public MockSpellRepository() {
        super();
    }

    @Override
    public void init() {
//        List<Spell> list = MockDataGenerator.createAbilities();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Spell entity) {
        if (entity.getId() == null) {
            entity.setId(idSpell++);
        }
    }

}
