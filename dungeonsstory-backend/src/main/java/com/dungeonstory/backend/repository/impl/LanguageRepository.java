package com.dungeonstory.backend.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.repository.AbstractRepository;

public class LanguageRepository extends AbstractRepository<Language, Long> {

    private static final long serialVersionUID = -6827271738126173159L;

    @Override
    protected Class<Language> getEntityClass() {
        return Language.class;
    }

    public List<Language> getLanguagesNotInRace(Race race) {
        entityManager.getTransaction().begin();
        TypedQuery<Language> query = entityManager.createNamedQuery(Language.LANGUAGES_NOT_IN_RACE, getEntityClass());
        query.setParameter("raceId", race.getId());
        List<Language> resultList = query.getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    public List<Language> getUnassignedLanguages(Character character) {
        if (character.getId() == null) {
            return findAll();
        }
        entityManager.getTransaction().begin();
        TypedQuery<Language> query = entityManager.createNamedQuery(Language.UNASSIGNED_LANGUAGES, getEntityClass());
        query.setParameter("raceId", character.getId());
        List<Language> resultList = query.getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

}
