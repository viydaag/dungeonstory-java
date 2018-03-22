package com.dungeonstory.backend.service.mock;

import java.util.HashSet;
import java.util.Set;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.service.LanguageDataService;

public class MockLanguageService implements LanguageDataService {

    private static MockLanguageService instance = null;

    public static synchronized MockLanguageService getInstance() {
        if (instance == null) {
            instance = new MockLanguageService();
        }
        return instance;
    }

    private MockLanguageService() {
        super();
    }

    @Override
    public Set<Language> getLanguagesNotInRace(Race race) {
        // TODO Auto-generated method stub
        return new HashSet<>();
    }

    @Override
    public Set<Language> getUnassignedLanguages(Character character) {
        // TODO Auto-generated method stub
        return new HashSet<>();
    }

}
