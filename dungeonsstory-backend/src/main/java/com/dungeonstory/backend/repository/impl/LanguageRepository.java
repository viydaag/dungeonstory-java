package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.repository.AbstractRepository;

public class LanguageRepository extends AbstractRepository<Language, Long> {

    @Override
    protected Class<Language> getEntityClass() {
        return Language.class;
    }

}
