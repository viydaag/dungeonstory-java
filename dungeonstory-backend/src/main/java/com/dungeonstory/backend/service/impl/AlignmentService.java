package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.factory.impl.AlignmentFactory;
import com.dungeonstory.backend.repository.impl.AlignmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.AlignmentDataService;

public class AlignmentService extends AbstractDataService<Alignment, Long> implements AlignmentDataService {

	private static final long serialVersionUID = 6724779422597105026L;
	
	private static AlignmentService instance = null;
    private static AlignmentRepository repo;

    public static synchronized AlignmentService getInstance() {
        if (instance == null) {
            instance = new AlignmentService();
        }
        return instance;
    }

    private AlignmentService() {
        super();
        setEntityFactory(new AlignmentFactory());
        repo = new AlignmentRepository();
        setRepository(repo);
    }

    @Override
    public List<Alignment> findAllPlayable() {
        return repo.findAllPlayable();
    }

}
