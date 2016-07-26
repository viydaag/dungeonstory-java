package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.factory.impl.ClassFactory;
import com.dungeonstory.backend.repository.mock.MockClassRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockClassService extends AbstractDataService<DSClass, Long> {

    private static MockClassService instance = null;

    public static synchronized MockClassService getInstance() {
        if (instance == null) {
            instance = new MockClassService();
        }
        return instance;
    }

    private MockClassService() {
        super();
        setEntityFactory(new ClassFactory());
        setRepository(new MockClassRepository());
    }

}
