package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Tool;
import com.dungeonstory.backend.factory.impl.EquipmentFactory;
import com.dungeonstory.backend.repository.impl.EquipmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.EquipmentDataService;

public class EquipmentService extends AbstractDataService<Equipment, Long> implements EquipmentDataService {

	private static final long serialVersionUID = 5384718595945797301L;
	
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
    
    @Override
    public List<Equipment> findAllPurchasable() {
        return ((EquipmentRepository) entityRepository).findAllPurchasable();
    }
    
    @Override
    public List<Equipment> findAllSellable() {
    	return ((EquipmentRepository) entityRepository).findAllSellable();
    }

    @Override
    public List<Tool> findAllTools() {
        return ((EquipmentRepository) entityRepository).findAllTools();
    }

}
