package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.factory.impl.AdventureFactory;
import com.dungeonstory.backend.repository.impl.AdventureRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class AdventureService extends AbstractDataService<Adventure, Long> {

	private static final long serialVersionUID = -3637998347027964634L;
	
	private static AdventureService instance = null;

    public static synchronized AdventureService getInstance() {
        if (instance == null) {
            instance = new AdventureService();
        }
        return instance;
    }

    private AdventureService() {
        super();
        setEntityFactory(new AdventureFactory());
        setRepository(new AdventureRepository());
    }

}
