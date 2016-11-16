package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.factory.impl.ClassFactory;
import com.dungeonstory.backend.repository.impl.ClassRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class ClassService extends AbstractDataService<DSClass, Long> {

	private static final long serialVersionUID = -188893833207480803L;
	
	private static ClassService instance = null;

    public static synchronized ClassService getInstance() {
        if (instance == null) {
            instance = new ClassService();
        }
        return instance;
    }

    private ClassService() {
        super();
        setEntityFactory(new ClassFactory());
        setRepository(new ClassRepository());
    }
    
}
