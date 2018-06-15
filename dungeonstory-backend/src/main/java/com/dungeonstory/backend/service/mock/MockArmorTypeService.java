package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.service.ArmorTypeDataService;

public class MockArmorTypeService implements ArmorTypeDataService {

    private static MockArmorTypeService instance = null;

    public static synchronized MockArmorTypeService getInstance() {
        if (instance == null) {
            instance = new MockArmorTypeService();
        }
        return instance;
    }

    private MockArmorTypeService() {
        super();
    }

}
