package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockAlignmentRepository extends MockAbstractRepository<Alignment> {

    private static Long idAlignment = 1L;

    public MockAlignmentRepository() {
        super();
    }

    @Override
    public void init() {
        List<Alignment> list = MockDataGenerator.createAlignments();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Alignment entity) {
        if (entity.getId() == null) {
            entity.setId(idAlignment++);
        }
    }

}
