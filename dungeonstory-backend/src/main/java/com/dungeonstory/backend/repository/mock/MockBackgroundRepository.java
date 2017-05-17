package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockBackgroundRepository extends MockAbstractRepository<Background> {

    private static Long idBackground = 1L;

    public MockBackgroundRepository() {
        super();
    }

    @Override
    public void init() {
        List<Background> list = MockDataGenerator.createBackgrounds();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Background entity) {
        if (entity.getId() == null) {
            entity.setId(idBackground++);
        }
    }

}
