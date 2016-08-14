package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.factory.impl.AlignmentFactory;
import com.dungeonstory.backend.repository.impl.AlignmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class AlignmentService extends AbstractDataService<Alignment, Long> {

	private static final long serialVersionUID = 6724779422597105026L;
	
	private static AlignmentService instance = null;

    public static synchronized AlignmentService getInstance() {
        if (instance == null) {
            instance = new AlignmentService();
        }
        return instance;
    }

    private AlignmentService() {
        super();
        setEntityFactory(new AlignmentFactory());
        setRepository(new AlignmentRepository());
    }

}
