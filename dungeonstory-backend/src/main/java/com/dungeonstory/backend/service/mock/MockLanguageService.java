package com.dungeonstory.backend.service.mock;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.repository.mock.MockLanguageRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.LanguageDataService;

public class MockLanguageService extends AbstractDataService<Language, Long> implements LanguageDataService {

    private static final long serialVersionUID = 3713358506919598497L;

    private static MockLanguageService instance = null;

    public static synchronized MockLanguageService getInstance() {
        if (instance == null) {
            instance = new MockLanguageService();
        }
        return instance;
    }

    private MockLanguageService() {
        super();
        setEntityFactory(() -> new Language());
        setRepository(new MockLanguageRepository());
    }

    @Override
    public List<Language> getLanguagesNotInRace(Race race) {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    @Override
    public List<Language> getUnassignedLanguages(Character character) {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

}
