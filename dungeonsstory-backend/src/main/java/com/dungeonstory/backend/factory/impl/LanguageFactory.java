package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.factory.Factory;

public class LanguageFactory implements Factory<Language> {

    @Override
    public Language create() {
        return new Language();
    }

}
