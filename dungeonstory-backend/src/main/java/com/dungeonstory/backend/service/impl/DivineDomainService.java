package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.factory.impl.DivineDomainFactory;
import com.dungeonstory.backend.repository.impl.DivineDomainRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class DivineDomainService extends AbstractDataService<DivineDomain, Long> {

    private static final long serialVersionUID = 6468595169787824606L;

    private static DivineDomainService instance = null;
    private static DivineDomainRepository repo;

    public static synchronized DivineDomainService getInstance() {
        if (instance == null) {
            repo = new DivineDomainRepository();
            instance = new DivineDomainService();
        }
        return instance;
    }

    private DivineDomainService() {
        super();
        setEntityFactory(new DivineDomainFactory());
        setRepository(repo);
    }

    public List<DivineDomain> findAllByDeity(Deity deity) {
        return repo.findAllByDeity(deity);
    }

}
