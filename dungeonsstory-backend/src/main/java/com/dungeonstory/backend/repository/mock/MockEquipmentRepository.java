package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Equipment;

public class MockEquipmentRepository extends MockAbstractRepository<Equipment> {

    private static Long idEquipment = 1L;

    public MockEquipmentRepository() {
        super();
    }

    @Override
    public void init() {
//        List<Equipment> list = MockDataGenerator.createAbilities();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Equipment entity) {
        if (entity.getId() == null) {
            entity.setId(idEquipment++);
        }
    }
    
    public List<Equipment> findAllPurchasable() {
        return findAll();
    }
    
    public List<Equipment> findAllSellable() {
    	return findAll();
    }

}
