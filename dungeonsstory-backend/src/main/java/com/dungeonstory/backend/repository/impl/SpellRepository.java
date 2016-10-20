package com.dungeonstory.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.repository.AbstractRepository;

public class SpellRepository extends AbstractRepository<Spell, Long> {

    private static final long serialVersionUID = -513470268159857579L;

    @Override
    protected Class<Spell> getEntityClass() {
        return Spell.class;
    }

    public List<Spell> findAllSpellsByLevel(int level) {
        TypedQuery<Spell> query = entityManager.createQuery("SELECT e FROM Spell e WHERE e.level = :level",
                getEntityClass());
        query.setParameter("level", level);
        return query.getResultList();
    }

    public List<Spell> findAllUnknownSpellsByLevel(int level, Long characterId) {
        if (characterId != null) {
            TypedQuery<Spell> query = entityManager.createQuery(
                    "SELECT s FROM Spell s WHERE s.level = :level AND s.id NOT IN (SELECT cks.spellId CharacterKnownSpells WHERE cks.characterId = :characterId)",
                    getEntityClass());
            query.setParameter("level", level);
            return query.getResultList();
        } else {
            return findAllSpellsByLevel(level);
        }
    }

    public List<Spell> findAllKnownSpellsByLevel(int level, Long characterId) {
        if (characterId != null) {
            TypedQuery<Spell> query = entityManager.createQuery(
                    "SELECT s FROM Spell s WHERE s.level = :level AND s.id IN (SELECT cks.spellId CharacterKnownSpells WHERE cks.characterId = :characterId)",
                    getEntityClass());
            query.setParameter("level", level);
            return query.getResultList();
        } else {
            return new ArrayList<Spell>();
        }
    }

    public List<Spell> findAllSpellsSortedByLevelAndName() {
        TypedQuery<Spell> query = entityManager.createQuery("SELECT e FROM Spell e ORDER BY level ASC, name ASC",
                getEntityClass());
        return query.getResultList();
    }

}
