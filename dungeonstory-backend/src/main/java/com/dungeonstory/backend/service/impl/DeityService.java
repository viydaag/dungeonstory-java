package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.repository.impl.DeityRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.DeityDataService;

public class DeityService extends AbstractDataService<Deity, Long> implements DeityDataService {

    private static final long serialVersionUID = 6468595169787824606L;

    private static DeityService instance = null;
    private DeityRepository     repository;

    public static synchronized DeityService getInstance() {
        if (instance == null) {
            instance = new DeityService();
        }
        return instance;
    }

    private DeityService() {
        super();
        setEntityFactory(() -> new Deity());
        repository = new DeityRepository();
        setRepository(repository);
    }

    @Override
    public List<Deity> findAllByDomain(ClassSpecialization domain) {
        return repository.findAllByDomain(domain);
    }


}
