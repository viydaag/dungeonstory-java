package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.repository.mock.MockClassRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ClassDataService;

public class MockClassService extends AbstractDataService<DSClass, Long> implements ClassDataService {

    private static final long serialVersionUID = -6172229274675262653L;

    private static MockClassService instance = null;

    public static synchronized MockClassService getInstance() {
        if (instance == null) {
            instance = new MockClassService();
        }
        return instance;
    }

    private MockClassService() {
        super();
        setEntityFactory(() -> new DSClass());
        setRepository(new MockClassRepository());
    }

}
