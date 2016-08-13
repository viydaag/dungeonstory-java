package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Bracer;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.factory.Factory;

public class EquipmentFactory implements Factory<Equipment> {

    @Override
    public Equipment create() {
        return new Bracer();	//dummy equipment
    }

}
