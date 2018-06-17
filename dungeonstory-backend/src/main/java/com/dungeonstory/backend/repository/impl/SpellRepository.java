package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.repository.AbstractRepository;
import com.dungeonstory.backend.repository.JPAService;

public class SpellRepository extends AbstractRepository<Spell, Long> {

    private static final long serialVersionUID = -513470268159857579L;

    @Override
    protected Class<Spell> getEntityClass() {
        return Spell.class;
    }

    public List<Spell> findAllSpellsByLevel(int level) {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<Spell> query = entityManager.createNamedQuery(Spell.ALL_SPELLS_BY_LEVEL, getEntityClass());
            query.setParameter("level", level);
            List<Spell> resultList = query.getResultList();
            return resultList;
        });
    }

    public List<Spell> findAllClassSpellsByLevel(int level, Long classId) {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<Spell> query = entityManager.createNamedQuery(Spell.ALL_CLASS_SPELLS_BY_LEVEL, getEntityClass());
            query.setParameter("level", level);
            query.setParameter("classId", classId);
            List<Spell> resultList = query.getResultList();
            return resultList;
        });
    }

    public List<Spell> findAllUnknownClassSpellsByLevel(int level, Long characterId, Long classId) {
        if (characterId != null) {
            return JPAService.getInTransaction(entityManager -> {
                TypedQuery<Spell> query = entityManager.createNamedQuery(Spell.ALL_UNKNOWN_CLASS_SPELLS_BY_LEVEL,
                        getEntityClass());
                query.setParameter("level", level);
                query.setParameter("classId", classId);
                query.setParameter("characterId", characterId);
                List<Spell> resultList = query.getResultList();
                return resultList;
            });
        } else {
            return findAllClassSpellsByLevel(level, classId);
        }
    }

    public List<Spell> findAllKnownClassSpellsByLevel(int level, Long characterId, Long classId) {
        if (characterId != null) {
            return JPAService.getInTransaction(entityManager -> {
                TypedQuery<Spell> query = entityManager.createNamedQuery(Spell.ALL_KNOWN_CLASS_SPELLS_BY_LEVEL,
                        getEntityClass());
                query.setParameter("level", level);
                query.setParameter("classId", classId);
                query.setParameter("characterId", characterId);
                List<Spell> resultList = query.getResultList();
                return resultList;
            });
        } else {
            return new ArrayList<Spell>();
        }
    }

    public List<Spell> findAllSpellsSortedByLevelAndName() {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<Spell> query = entityManager.createNamedQuery(Spell.ALL_SPELLS_SORTED_BY_LEVEL_AND_NAME,
                    getEntityClass());
            List<Spell> resultList = query.getResultList();
            return resultList;
        });
    }

}
