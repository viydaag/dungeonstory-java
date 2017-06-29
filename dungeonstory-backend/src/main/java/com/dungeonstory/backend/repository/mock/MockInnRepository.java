package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Inn;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockInnRepository extends MockAbstractRepository<Inn> {

    private static Long idInn = 1L;

    public MockInnRepository() {
        super();
    }

    @Override
    public void init() {
        List<Inn> list = MockDataGenerator.createInns();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Inn entity) {
        if (entity.getId() == null) {
            entity.setId(idInn++);
        }
    }

}
