package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.factory.impl.LanguageFactory;
import com.dungeonstory.backend.repository.impl.LanguageRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class LanguageService extends AbstractDataService<Language, Long> {

    private static final long serialVersionUID = -8778708705346635028L;
    
    private static LanguageService instance = null;

    public static synchronized LanguageService getInstance() {
        if (instance == null) {
            instance = new LanguageService();
        }
        return instance;
    }

    private LanguageService() {
        super();
        setEntityFactory(new LanguageFactory());
        setRepository(new LanguageRepository());
    }

}
