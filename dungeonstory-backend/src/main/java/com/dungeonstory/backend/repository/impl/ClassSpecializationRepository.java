package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.repository.AbstractRepository;
import com.dungeonstory.backend.repository.JPAService;

public class ClassSpecializationRepository extends AbstractRepository<ClassSpecialization, Long> {

    private static final long serialVersionUID = 4560405765272948577L;

    @Override
    protected Class<ClassSpecialization> getEntityClass() {
        return ClassSpecialization.class;
    }
    
    public List<ClassSpecialization> findAllDivineDomainSpecializations() {
        return JPAService.getInTransaction(entityManager -> {
            List<ClassSpecialization> result = new ArrayList<>();
            TypedQuery<ClassSpecialization> query = entityManager.createNamedQuery(ClassSpecialization.FIND_ALL_DIVINE_DOMAIN_SPEC, getEntityClass());
            query.setParameter("name", "Domaine%");
            result = query.getResultList();
            return result;
        });
    }

}
