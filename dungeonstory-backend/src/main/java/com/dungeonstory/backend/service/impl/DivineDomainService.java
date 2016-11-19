package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.factory.impl.DivineDomainFactory;
import com.dungeonstory.backend.repository.impl.DivineDomainRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class DivineDomainService extends AbstractDataService<DivineDomain, Long> {

    private static final long serialVersionUID = 6468595169787824606L;

    private static DivineDomainService instance = null;

    public static synchronized DivineDomainService getInstance() {
        if (instance == null) {
            instance = new DivineDomainService();
        }
        return instance;
    }

    private DivineDomainService() {
        super();
        setEntityFactory(new DivineDomainFactory());
        setRepository(new DivineDomainRepository());
    }

}
