package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.repository.AbstractRepository;

public class EquipmentRepository extends AbstractRepository<Equipment, Long> {

    @Override
    protected Class<Equipment> getEntityClass() {
        return Equipment.class;
    }

}
