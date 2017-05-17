package com.dungeonstory.backend.repository.mock;

import java.util.List;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockDivineDomainRepository extends MockAbstractRepository<DivineDomain> {

    public static Long idUser = 1L;

    public MockDivineDomainRepository() {
        super();
    }

    @Override
    public void init() {
        List<DivineDomain> list = MockDataGenerator.createDivineDomains();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(DivineDomain entity) {
        if (entity.getId() == null) {
            entity.setId(idUser++);
        }
    }

    public List<DivineDomain> findAllByDeity(Deity deity) {
        return entities.values().stream().filter(domain -> domain.getDeities().contains(deity)).collect(Collectors.toList());
    }

}
