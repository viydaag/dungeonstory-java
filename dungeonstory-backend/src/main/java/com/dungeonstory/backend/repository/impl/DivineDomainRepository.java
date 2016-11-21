package com.dungeonstory.backend.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.repository.AbstractRepository;

public class DivineDomainRepository extends AbstractRepository<DivineDomain, Long> {

    private static final long serialVersionUID = -4865427098643423403L;

    @Override
    protected Class<DivineDomain> getEntityClass() {
        return DivineDomain.class;
    }

    public List<DivineDomain> findAllByDeity(Deity deity) {
        entityManager.getTransaction().begin();
        TypedQuery<DivineDomain> query = entityManager.createNamedQuery(DivineDomain.FIND_ALL_BY_DEITY,
                getEntityClass());
        query.setParameter("deityId", deity.getId());
        List<DivineDomain> resultList = query.getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

}
