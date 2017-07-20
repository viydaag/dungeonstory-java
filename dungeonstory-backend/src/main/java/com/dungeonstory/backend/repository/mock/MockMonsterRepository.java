package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockMonsterRepository extends MockAbstractRepository<Monster> {

    private static Long idMonster = 1L;

    public MockMonsterRepository() {
        super();
    }

    @Override
    public void init() {
        List<Monster> list = MockDataGenerator.createMonsters();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Monster entity) {
        if (entity.getId() == null) {
            entity.setId(idMonster++);
        }
    }

}
