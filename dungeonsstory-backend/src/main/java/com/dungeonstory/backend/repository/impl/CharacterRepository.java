package com.dungeonstory.backend.repository.impl;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.repository.AbstractRepository;

public class CharacterRepository extends AbstractRepository<Character, Long> {

    private static final long serialVersionUID = 6507158340356635917L;

    @Override
    protected Class<Character> getEntityClass() {
        return Character.class;
    }

    public CharacterClass getAssignedClass(Character character, DSClass classe) {
        TypedQuery<CharacterClass> query = null;
        query = entityManager.createQuery(
                "SELECT e FROM CharacterClass e WHERE e.character = :character AND e.classe = :classe",
                CharacterClass.class);
        query.setParameter("character", character);
        query.setParameter("classe", classe);
        return query.getSingleResult();
    }

}
