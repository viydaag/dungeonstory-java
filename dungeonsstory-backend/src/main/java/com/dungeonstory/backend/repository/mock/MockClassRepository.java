package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.DSClass;

public class MockClassRepository extends MockAbstractRepository<DSClass> {
    
    private static Long idClass = 1L;

    public MockClassRepository() {
        super();
    }

    @Override
    public void init() {
        //TODO
//        List<DSClass> list = MockDataGenerator.createAbilities();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(DSClass entity) {
        if (entity.getId() == null) {
            entity.setId(idClass++);
        }
    }

}
