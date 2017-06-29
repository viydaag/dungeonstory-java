package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Temple;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockTempleRepository extends MockAbstractRepository<Temple> {

    private static Long idTemple = 1L;

    public MockTempleRepository() {
        super();
    }

    @Override
    public void init() {
        List<Temple> list = MockDataGenerator.createTemples();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Temple entity) {
        if (entity.getId() == null) {
            entity.setId(idTemple++);
        }
    }

}
