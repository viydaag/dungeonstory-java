package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.factory.impl.ArmorTypeFactory;
import com.dungeonstory.backend.repository.impl.ArmorTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class ArmorTypeService extends AbstractDataService<ArmorType, Long> {

    private static ArmorTypeService instance = null;

    public static synchronized ArmorTypeService getInstance() {
        if (instance == null) {
            instance = new ArmorTypeService();
        }
        return instance;
    }

    private ArmorTypeService() {
        super();
        setEntityFactory(new ArmorTypeFactory());
        setRepository(new ArmorTypeRepository());
    }

}
