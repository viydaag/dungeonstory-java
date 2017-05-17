package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockDeityRepository extends MockAbstractRepository<Deity> {

    private static Long idDeity = 1L;

    public MockDeityRepository() {
        super();
    }

    @Override
    public void init() {
        List<Deity> list = MockDataGenerator.createDeities();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Deity entity) {
        if (entity.getId() == null) {
            entity.setId(idDeity++);
        }
    }

}
