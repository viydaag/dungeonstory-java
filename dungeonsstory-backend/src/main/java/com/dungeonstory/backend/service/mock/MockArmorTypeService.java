package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.factory.impl.ArmorTypeFactory;
import com.dungeonstory.backend.repository.mock.MockArmorTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockArmorTypeService extends AbstractDataService<ArmorType, Long> {

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
        setEntityFactory(new ArmorTypeFactory());
        setRepository(new MockArmorTypeRepository());
    }

}
