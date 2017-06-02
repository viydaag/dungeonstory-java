package com.dungeonstory.backend.service.mock;

import java.util.List;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.repository.mock.MockAlignmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.AlignmentDataService;

public class MockAlignmentService extends AbstractDataService<Alignment, Long> implements AlignmentDataService {

    private static final long serialVersionUID = 5669347444406948099L;

    private static MockAlignmentService instance = null;

    public static synchronized MockAlignmentService getInstance() {
        if (instance == null) {
            instance = new MockAlignmentService();
        }
        return instance;
    }

    private MockAlignmentService() {
        super();
        setEntityFactory(() -> new Alignment());
        setRepository(new MockAlignmentRepository());
    }

    @Override
    public List<Alignment> findAllPlayable() {
        return entityRepository.findAll().stream().filter(a -> a.getPlayable() == true).collect(Collectors.toList());
    }

}
