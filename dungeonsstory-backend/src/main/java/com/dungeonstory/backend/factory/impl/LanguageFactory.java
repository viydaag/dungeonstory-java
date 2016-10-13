package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.factory.Factory;

public class LanguageFactory implements Factory<Language> {

    private static final long serialVersionUID = -1790963877782507136L;

    @Override
    public Language create() {
        return new Language();
    }

}
