package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.repository.impl.ArmorTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ArmorTypeDataService;

public class ArmorTypeService extends AbstractDataService<ArmorType, Long> implements ArmorTypeDataService {

    private static final long serialVersionUID = 7603289460986668111L;

    private static ArmorTypeService instance = null;

    public static synchronized ArmorTypeService getInstance() {
        if (instance == null) {
            instance = new ArmorTypeService();
        }
        return instance;
    }

    private ArmorTypeService() {
        super();
        setEntityFactory(() -> new ArmorType());
        setRepository(new ArmorTypeRepository());
    }

}
