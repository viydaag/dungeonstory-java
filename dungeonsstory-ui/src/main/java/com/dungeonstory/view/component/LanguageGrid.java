package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Language;

public class LanguageGrid extends BeanGrid<Language> {

    private static final long serialVersionUID = -8818716671121858460L;

    public LanguageGrid() {
        super(Language.class);
        withColumns("name", "script");
        withHeaderCaption("Nom", "Alphabet");
    }

}
