package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.factory.impl.LanguageFactory;
import com.dungeonstory.backend.repository.mock.MockLanguageRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockLanguageService extends AbstractDataService<Language, Long> {

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
        setEntityFactory(new LanguageFactory());
        setRepository(new MockLanguageRepository());
    }

}
