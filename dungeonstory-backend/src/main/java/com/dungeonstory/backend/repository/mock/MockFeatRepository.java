package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockFeatRepository extends MockAbstractRepository<Feat> {

    private static Long idFeat = 1L;

    public MockFeatRepository() {
        super();
    }

    @Override
    public void init() {
        List<Feat> list = MockDataGenerator.createFeats();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Feat entity) {
        if (entity.getId() == null) {
            entity.setId(idFeat++);
        }
    }
    

}
