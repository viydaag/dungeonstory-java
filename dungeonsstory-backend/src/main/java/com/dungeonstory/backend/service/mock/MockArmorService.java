package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.factory.impl.ArmorFactory;
import com.dungeonstory.backend.repository.mock.MockArmorRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockArmorService extends AbstractDataService<Armor, Long> {

    private static MockArmorService instance = null;

    public static synchronized MockArmorService getInstance() {
        if (instance == null) {
            instance = new MockArmorService();
        }
        return instance;
    }

    private MockArmorService() {
        super();
        setEntityFactory(new ArmorFactory());
        setRepository(new MockArmorRepository());
    }

}
