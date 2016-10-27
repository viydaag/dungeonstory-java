package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.factory.impl.LanguageFactory;
import com.dungeonstory.backend.repository.impl.LanguageRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.LanguageDataService;

public class LanguageService extends AbstractDataService<Language, Long> implements LanguageDataService {

    private static final long serialVersionUID = -8778708705346635028L;
    
    private static LanguageService instance = null;

    private LanguageRepository repo = new LanguageRepository();

    public static synchronized LanguageService getInstance() {
        if (instance == null) {
            instance = new LanguageService();
        }
        return instance;
    }

    private LanguageService() {
        super();
        setEntityFactory(new LanguageFactory());
        setRepository(repo);
    }

    @Override
    public List<Language> getLanguagesNotInRace(Race race) {
        return repo.getLanguagesNotInRace(race);
    }

    @Override
    public List<Language> getUnassignedLanguages(Character character) {
        return repo.getUnassignedLanguages(character);
    }

}
