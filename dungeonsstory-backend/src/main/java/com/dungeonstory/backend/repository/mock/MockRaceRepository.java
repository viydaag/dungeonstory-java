package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockRaceRepository extends MockAbstractRepository<Race> {

    private static Long idRace = 1L;

    public MockRaceRepository() {
        super();
    }

    @Override
    public void init() {
        List<Race> list = MockDataGenerator.createRaces();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Race entity) {
        if (entity.getId() == null) {
            entity.setId(idRace++);
        }
    }

}
