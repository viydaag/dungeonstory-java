package com.dungeonstory.backend.service.impl;

import java.util.EnumSet;
import java.util.Set;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.service.LanguageDataService;

public class LanguageService implements LanguageDataService {

    private static LanguageService instance = null;

    public static synchronized LanguageService getInstance() {
        if (instance == null) {
            instance = new LanguageService();
        }
        return instance;
    }

    private LanguageService() {
        super();
    }

    @Override
    public Set<Language> getLanguagesNotInRace(Race race) {
        return EnumSet.complementOf(EnumSet.copyOf(race.getLanguages()));
    }

    @Override
    public Set<Language> getUnassignedLanguages(Character character) {
        return EnumSet.complementOf(EnumSet.copyOf(character.getLanguages()));
    }

}
