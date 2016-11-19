package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockLevelRepository extends MockAbstractRepository<Level> {

    public MockLevelRepository() {
        super();
    }

    @Override
    public void init() {
        List<Level> list = MockDataGenerator.createLevels();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Level entity) {
        //do nothing, level ids must be already set
    }

}
