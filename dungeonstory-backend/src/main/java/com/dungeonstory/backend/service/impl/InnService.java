package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Inn;
import com.dungeonstory.backend.repository.impl.InnRepository;
import com.dungeonstory.backend.service.InnDataService;
import com.dungeonstory.backend.service.AbstractDataService;

public class InnService extends AbstractDataService<Inn, Long> implements InnDataService {

    private static final long serialVersionUID = 1551756677808051064L;
    
    private static InnService instance = null;

    public static synchronized InnService getInstance() {
        if (instance == null) {
            instance = new InnService();
        }
        return instance;
    }

    private InnService() {
        super();
        setEntityFactory(() -> new Inn());
        setRepository(new InnRepository());
    }

}
