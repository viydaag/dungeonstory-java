package com.dungeonstory.backend.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.repository.AbstractRepository;

public class FeatRepository extends AbstractRepository<Feat, Long> {

    @Override
    protected Class<Feat> getEntityClass() {
        return Feat.class;
    }
    
    public List<Feat> findAllFeats() {
        TypedQuery<Feat> query = entityManager.createQuery("SELECT e FROM Feat e WHERE e.isClassFeature = 0", getEntityClass());
        return query.getResultList();
    }
    
    public List<Feat> findAllClassFeatures() {
        TypedQuery<Feat> query = entityManager.createQuery("SELECT e FROM Feat e WHERE e.isClassFeature = 1", getEntityClass());
        return query.getResultList();
    }

}
