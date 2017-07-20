package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ClassSpecializationRepository extends AbstractRepository<ClassSpecialization, Long> {

    private static final long serialVersionUID = 4560405765272948577L;

    @Override
    protected Class<ClassSpecialization> getEntityClass() {
        return ClassSpecialization.class;
    }
    
    public List<ClassSpecialization> findAllDivineDomainSpecializations() {
        List<ClassSpecialization> result = new ArrayList<>();
        TypedQuery<ClassSpecialization> query = null;
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        query = entityManager.createNamedQuery(ClassSpecialization.FIND_ALL_DIVINE_DOMAIN_SPEC, getEntityClass());
        query.setParameter("name", "Domaine%");
        result = query.getResultList();
        transaction.commit();
        return result;
    }

}
