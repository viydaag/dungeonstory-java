package com.dungeonstory.backend.service.mock;

import java.util.List;

import com.dungeonstory.backend.data.Bracer;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Tool;
import com.dungeonstory.backend.repository.mock.MockEquipmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.EquipmentDataService;

public class MockEquipmentService extends AbstractDataService<Equipment, Long> implements EquipmentDataService {

    private static final long serialVersionUID = -5951950480149970916L;

    private static MockEquipmentService instance = null;

    public static synchronized MockEquipmentService getInstance() {
        if (instance == null) {
            instance = new MockEquipmentService();
        }
        return instance;
    }

    private MockEquipmentService() {
        super();
        setEntityFactory(() -> new Bracer());
        setRepository(new MockEquipmentRepository());
    }

    @Override
    public List<Equipment> findAllPurchasable() {
        return ((MockEquipmentRepository) entityRepository).findAllPurchasable();
    }

    @Override
    public List<Equipment> findAllSellable() {
        return ((MockEquipmentRepository) entityRepository).findAllSellable();
    }

    @Override
    public List<Tool> findAllTools() {
        return ((MockEquipmentRepository) entityRepository).findAllTools();
    }

}
