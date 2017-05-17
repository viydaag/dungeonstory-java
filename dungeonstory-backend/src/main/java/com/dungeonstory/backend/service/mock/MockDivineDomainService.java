package com.dungeonstory.backend.service.mock;

import java.util.List;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.repository.mock.MockDivineDomainRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.DivineDomainDataService;

public class MockDivineDomainService extends AbstractDataService<DivineDomain, Long> implements DivineDomainDataService {

    private static final long serialVersionUID = -1006074376819247886L;
    
    private static MockDivineDomainService instance = null;

    public static synchronized MockDivineDomainService getInstance() {
        if (instance == null) {
            instance = new MockDivineDomainService();
        }
        return instance;
    }

    private MockDivineDomainService() {
        super();
        setEntityFactory(() -> new DivineDomain());
        setRepository(new MockDivineDomainRepository());
    }

    @Override
    public List<DivineDomain> findAllByDeity(Deity deity) {
        return ((MockDivineDomainRepository) entityRepository).findAllByDeity(deity);
    }

}
