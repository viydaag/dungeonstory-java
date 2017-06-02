package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.repository.impl.AbilityRepository;
import com.dungeonstory.backend.service.AbilityDataService;
import com.dungeonstory.backend.service.AbstractDataService;

public class AbilityService extends AbstractDataService<Ability, Long> implements AbilityDataService {

    private static final long serialVersionUID = 6468595169787824606L;

    private static AbilityService instance = null;

    public static synchronized AbilityService getInstance() {
        if (instance == null) {
            instance = new AbilityService();
        }
        return instance;
    }

    private AbilityService() {
        super();
        setEntityFactory(() -> new Ability());
        setRepository(new AbilityRepository());
    }

}
