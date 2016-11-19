package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.Language;

public class MockLanguageRepository extends MockAbstractRepository<Language> {

    private static Long idLanguage = 1L;

    public MockLanguageRepository() {
        super();
    }

    @Override
    public void init() {
//        List<Language> list = MockDataGenerator.createLanguages();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Language entity) {
        if (entity.getId() == null) {
            entity.setId(idLanguage++);
        }
    }

}
