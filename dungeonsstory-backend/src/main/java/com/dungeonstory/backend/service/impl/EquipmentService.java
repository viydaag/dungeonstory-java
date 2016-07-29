package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.factory.impl.EquipmentFactory;
import com.dungeonstory.backend.repository.impl.EquipmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class EquipmentService extends AbstractDataService<Equipment, Long> {

    private static EquipmentService instance = null;

    public static synchronized EquipmentService getInstance() {
        if (instance == null) {
            instance = new EquipmentService();
        }
        return instance;
    }

    private EquipmentService() {
        super();
        setEntityFactory(new EquipmentFactory());
        setRepository(new EquipmentRepository());
    }

}
