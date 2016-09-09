package com.dungeonstory.backend.repository.impl;

import javax.persistence.Query;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ClassRepository extends AbstractRepository<DSClass, Long> {

    @Override
    protected Class<DSClass> getEntityClass() {
        return DSClass.class;
    }
    
    public void deleteClassLevelBonusFeat(ClassLevelFeature bonusFeat) {
        if (bonusFeat == null) {
            return;
        }
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery(DSClass.deleteClassLevelBonusFeat);
        query.setParameter("classe", bonusFeat.getClasse());
        query.setParameter("level", bonusFeat.getLevel());
        query.setParameter("feat", bonusFeat.getFeat());
        int result = query.executeUpdate();
        if (Configuration.getInstance().isDebug()) {
            System.out.println("Nombre de class level bonus feat deleted = " + result);
        }
        entityManager.getTransaction().commit();
    }
    

}
