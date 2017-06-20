package com.dungeonstory.backend.service.mock;

import java.util.List;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.repository.mock.MockDeityRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.DeityDataService;

public class MockDeityService extends AbstractDataService<Deity, Long> implements DeityDataService {

    private static final long serialVersionUID = 3713358506919598497L;

    private static MockDeityService instance = null;

    public static synchronized MockDeityService getInstance() {
        if (instance == null) {
            instance = new MockDeityService();
        }
        return instance;
    }

    private MockDeityService() {
        super();
        setEntityFactory(() -> new Deity());
        setRepository(new MockDeityRepository());
    }

    @Override
    public List<Deity> findAllByDomain(ClassSpecialization domain) {
        return null;
    }

}
