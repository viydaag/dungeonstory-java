package com.dungeonstory.backend.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.repository.AbstractRepository;

public class AlignmentRepository extends AbstractRepository<Alignment, Long> {

    private static final long serialVersionUID = 5570800252737580747L;

    @Override
    protected Class<Alignment> getEntityClass() {
        return Alignment.class;
    }

    public List<Alignment> findAllPlayable() {
        entityManager.getTransaction().begin();
        TypedQuery<Alignment> query = entityManager.createNamedQuery(Alignment.FIND_ALL_PLAYABLE, getEntityClass());
        List<Alignment> resultList = query.getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

}
