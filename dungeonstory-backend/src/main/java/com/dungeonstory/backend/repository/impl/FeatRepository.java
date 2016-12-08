package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Feat> findAllFeats() {
        List<Feat> result = new ArrayList<Feat>();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Feat> query = entityManager.createNamedQuery(Feat.FIND_ALL_FEATS, getEntityClass());
        result = query.getResultList();
        transaction.commit();
        return result;
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
            result = findAllFeats();
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

    public List<Feat> findAllClassFeatures() {
        EntityTransaction transaction = entityManager.getTransaction();
        List<Feat> result = new ArrayList<Feat>();
        transaction.begin();
        TypedQuery<Feat> query = entityManager.createNamedQuery(Feat.FIND_ALL_CLASS_FEATURES, getEntityClass());
        result = query.getResultList();
        transaction.commit();
        return result;
    }

    public List<Feat> findAllClassFeaturesWithoutParent() {
        EntityTransaction transaction = entityManager.getTransaction();
        List<Feat> result = new ArrayList<Feat>();
        transaction.begin();
        TypedQuery<Feat> query = entityManager.createNamedQuery(Feat.FIND_ALL_CLASS_FEATURES_WITHOUT_PARENT,
                getEntityClass());
        result = query.getResultList();
        transaction.commit();
        return result;
    }

    public List<Feat> findAllClassFeaturesWithoutChildren() {
        List<Feat> allClassFeatures = findAllClassFeatures();
        return allClassFeatures.stream().filter(feature -> feature.getChildren().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Feat> findAllClassFeatureExcept(Feat feat) {
        List<Feat> result = new ArrayList<Feat>();
        TypedQuery<Feat> query = null;
        if (feat != null && feat.getId() != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            query = entityManager.createNamedQuery(Feat.FIND_ALL_CLASS_FEATURE_EXCEPT, getEntityClass());
            query.setParameter("featId", feat.getId());
            result = query.getResultList();
            transaction.commit();
        } else {
            result = findAllClassFeatures();
        }
        return result;
    }

}
