package com.dungeonstory.backend.repository.impl;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.enums.Feat;
import com.dungeonstory.backend.repository.AbstractRepository;
import com.dungeonstory.backend.repository.JPAService;

public class CharacterRepository extends AbstractRepository<Character, Long> {

    private static final long serialVersionUID = 6507158340356635917L;

    @Override
    protected Class<Character> getEntityClass() {
        return Character.class;
    }

    public CharacterClass getAssignedClass(Character character, DSClass classe) {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<CharacterClass> query = entityManager.createQuery(
                    "SELECT e FROM CharacterClass e WHERE e.character = :character AND e.classe = :classe", CharacterClass.class);
            query.setParameter("character", character);
            query.setParameter("classe", classe);
            return query.getSingleResult();
        });
    }
    
    @Override
    public synchronized void delete(Character entity) {
        if (entity.getId() == null) {
            return;
        }
        JPAService.executeInTransaction(em -> {
            Character character = em.getReference(getEntityClass(), entity.getId());
            User user = character.getUser();
            if (user != null) {
                user.setCharacter(null);
                em.merge(user);
            }
            character.setUser(null);
            em.remove(em.merge(character));
        });
    }


    public boolean hasFeat(Long characterId, Feat feat) {
        return JPAService.getInTransaction(entityManager -> {
            TypedQuery<Character> query = entityManager.createNamedQuery(Character.HAS_FEAT, Character.class);
            query.setParameter("characterId", characterId);
            query.setParameter("feat", feat);
            return !query.getResultList().isEmpty();
        });
    }

}
