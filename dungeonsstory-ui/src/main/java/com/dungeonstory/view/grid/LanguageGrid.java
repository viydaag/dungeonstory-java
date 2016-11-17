package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Language;

public class LanguageGrid extends DSGrid<Language> {

    private static final long serialVersionUID = -8818716671121858460L;

    public LanguageGrid() {
        super(Language.class);
        withProperties("name", "script");
        withHeaderCaption("Nom", "Alphabet");
    }

}
