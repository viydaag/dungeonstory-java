package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
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

    public List<Feat> findAllFeatsExcept(Feat feat) {
        List<Feat> result = new ArrayList<Feat>();
        TypedQuery<Feat> query = null;
        if (feat != null && feat.getId() != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            query = entityManager.createNamedQuery(Feat.FIND_ALL_FEATS_EXCEPT, getEntityClass());
            query.setParameter("featId", feat.getId());
            result = query.getResultList();
            transaction.commit();
        } else {
            result = findAll();
        }
        return result;
    }

    public List<Feat> findAllUnassignedFeats(Character character) {
        EntityTransaction transaction = entityManager.getTransaction();
        List<Feat> result = new ArrayList<Feat>();
        transaction.begin();
        TypedQuery<Feat> query = entityManager.createNamedQuery(Feat.FIND_ALL_UNASSIGNED_FEATS, getEntityClass());
        query.setParameter("characterId", character.getId());
        result = query.getResultList();
        transaction.commit();
        return result;
    }

}
