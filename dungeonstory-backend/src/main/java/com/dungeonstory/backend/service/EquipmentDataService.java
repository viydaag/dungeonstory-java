package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Tool;

public interface EquipmentDataService extends DataService<Equipment, Long> {

	public List<Equipment> findAllPurchasable();
    
    public List<Equipment> findAllSellable();
    
    public List<Tool> findAllTools();
	
}
