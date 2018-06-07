package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.repository.AbstractRepository;
import com.dungeonstory.backend.repository.JPAService;

public class ClassFeatureRepository
        extends AbstractRepository<ClassFeature, Long> {

    private static final long serialVersionUID = 3246726110749198104L;

    @Override
    protected Class<ClassFeature> getEntityClass() {
        return ClassFeature.class;
    }

    public List<ClassFeature> findAllClassFeaturesWithoutParent() {
        return JPAService.getInTransaction(entityManager -> {
            List<ClassFeature> result = new ArrayList<ClassFeature>();
            TypedQuery<ClassFeature> query = entityManager.createNamedQuery(ClassFeature.FIND_ALL_CLASS_FEATURES_WITHOUT_PARENT,
                    getEntityClass());
            result = query.getResultList();
            return result;
        });
    }

    public List<ClassFeature> findAllClassFeaturesWithoutChildren() {
        List<ClassFeature> allClassFeatures = findAll();
        return allClassFeatures.stream().filter(feature -> feature.getChildren().isEmpty()).collect(Collectors.toList());
    }

    public List<ClassFeature> findAllClassFeatureExcept(ClassFeature feat) {
        List<ClassFeature> result = new ArrayList<ClassFeature>();
        if (feat != null && feat.getId() != null) {
            result = JPAService.getInTransaction(entityManager -> {
                TypedQuery<ClassFeature> query = entityManager
                        .createNamedQuery(ClassFeature.FIND_ALL_CLASS_FEATURE_EXCEPT, getEntityClass());
                query.setParameter("featId", feat.getId());
                return query.getResultList();
            });
        } else {
            result = findAll();
        }
        return result;
    }

}
