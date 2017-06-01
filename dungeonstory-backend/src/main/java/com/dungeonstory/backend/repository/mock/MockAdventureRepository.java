package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Message;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockAdventureRepository extends MockAbstractRepository<Adventure> {

    private static Long idAdvenure = 1L;
    private static Long idMessage  = 1L;

    public MockAdventureRepository() {
        super();
    }

    @Override
    public void init() {
        List<Adventure> list = MockDataGenerator.createAdventures();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Adventure entity) {
        if (entity.getId() == null) {
            entity.setId(idAdvenure++);
        }
        for (Message message : entity.getMessages()) {
            message.setId(idMessage++);
        }
    }

}
