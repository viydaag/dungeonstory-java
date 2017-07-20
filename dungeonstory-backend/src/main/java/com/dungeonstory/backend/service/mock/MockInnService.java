package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Inn;
import com.dungeonstory.backend.repository.mock.MockInnRepository;
import com.dungeonstory.backend.service.InnDataService;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockInnService extends AbstractDataService<Inn, Long> implements InnDataService {

    private static final long serialVersionUID = -764290165537973565L;
    
    private static MockInnService instance = null;

    public static synchronized MockInnService getInstance() {
        if (instance == null) {
            instance = new MockInnService();
        }
        return instance;
    }

    private MockInnService() {
        super();
        setEntityFactory(() -> new Inn());
        setRepository(new MockInnRepository());
    }

}
