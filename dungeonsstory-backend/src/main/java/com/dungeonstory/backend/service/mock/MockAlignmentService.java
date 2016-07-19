package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.factory.impl.AlignmentFactory;
import com.dungeonstory.backend.repository.mock.MockAlignmentRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockAlignmentService extends AbstractDataService<Alignment, Long> {

    private static MockAlignmentService instance = null;

    public static synchronized MockAlignmentService getInstance() {
        if (instance == null) {
            instance = new MockAlignmentService();
        }
        return instance;
    }
    
    private MockAlignmentService() {
        super();
        setEntityFactory(new AlignmentFactory());
        setRepository(new MockAlignmentRepository());
    }
    
    
}
