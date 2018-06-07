package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.repository.AbstractRepository;
import com.dungeonstory.backend.repository.JPAService;

public class DeityRepository extends AbstractRepository<Deity, Long> {

    private static final long serialVersionUID = -6748031609537610763L;

    @Override
    protected Class<Deity> getEntityClass() {
        return Deity.class;
    }

    public List<Deity> findAllByDomain(ClassSpecialization domain) {
        return JPAService.getInTransaction(entityManager -> {
            List<Deity> result = new ArrayList<>();
            TypedQuery<Deity> query = null;
            query = entityManager.createNamedQuery(Deity.FIND_ALL_BY_DOMAIN, getEntityClass());
            query.setParameter("domainId", domain.getId());
            result = query.getResultList();
            return result;
        });
    }

}
