package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.service.ArmorTypeDataService;

public class ArmorTypeService implements ArmorTypeDataService {

    private static ArmorTypeService instance = null;

    public static synchronized ArmorTypeService getInstance() {
        if (instance == null) {
            instance = new ArmorTypeService();
        }
        return instance;
    }

    private ArmorTypeService() {
        super();
    }

}
