package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.repository.mock.MockArmorTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ArmorTypeDataService;

public class MockArmorTypeService extends AbstractDataService<ArmorType, Long> implements ArmorTypeDataService {

    private static final long serialVersionUID = 3817601379726992189L;

    private static MockArmorTypeService instance = null;

    public static synchronized MockArmorTypeService getInstance() {
        if (instance == null) {
            instance = new MockArmorTypeService();
        }
        return instance;
    }

    private MockArmorTypeService() {
        super();
        setEntityFactory(() -> new ArmorType());
        setRepository(new MockArmorTypeRepository());
    }

}
