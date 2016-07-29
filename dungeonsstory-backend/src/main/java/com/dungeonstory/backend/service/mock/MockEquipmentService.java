package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.factory.impl.EquipmentFactory;
import com.dungeonstory.backend.repository.mock.MockEquipmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockEquipmentService extends AbstractDataService<Equipment, Long> {

    private static MockEquipmentService instance = null;

    public static synchronized MockEquipmentService getInstance() {
        if (instance == null) {
            instance = new MockEquipmentService();
        }
        return instance;
    }

    private MockEquipmentService() {
        super();
        setEntityFactory(new EquipmentFactory());
        setRepository(new MockEquipmentRepository());
    }

}
