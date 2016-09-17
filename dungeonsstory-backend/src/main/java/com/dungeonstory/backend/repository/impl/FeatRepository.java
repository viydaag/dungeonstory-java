package com.dungeonstory.backend.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.repository.AbstractRepository;

public class FeatRepository extends AbstractRepository<Feat, Long> {

    private static final long serialVersionUID = -9187361469214976132L;

    @Override
    protected Class<Feat> getEntityClass() {
        return Feat.class;
    }
    
    public List<Feat> findAllFeats() {
        TypedQuery<Feat> query = entityManager.createQuery("SELECT e FROM Feat e WHERE e.isClassFeature = 0", getEntityClass());
        return query.getResultList();
    }
    
    public List<Feat> findAllFeatsExcept(Feat feat) {
        TypedQuery<Feat> query = null;
        if (feat != null && feat.getId() != null) {
            query = entityManager.createQuery("SELECT e FROM Feat e WHERE e.isClassFeature = 0 AND e.id != :featId", getEntityClass());
            query.setParameter("featId", feat.getId());
        } else {
            query = entityManager.createQuery("SELECT e FROM Feat e WHERE e.isClassFeature = 0", getEntityClass());
        }
        return query.getResultList();
    }
    
    public List<Feat> findAllUnassignedFeats(Character character) {
        TypedQuery<Feat> query = null;
        query = entityManager.createQuery("SELECT e FROM Feat e WHERE e.isClassFeature = 0 AND e.id NOT IN "
                + "(SELECT c.featId FROM CharacterFeat WHERE c.characterId = :characterId)", getEntityClass());
        query.setParameter("characterId", character.getId());
        return query.getResultList();
    }
    
    public List<Feat> findAllClassFeatures() {
        TypedQuery<Feat> query = entityManager.createQuery("SELECT e FROM Feat e WHERE e.isClassFeature = 1", getEntityClass());
        return query.getResultList();
    }

}
