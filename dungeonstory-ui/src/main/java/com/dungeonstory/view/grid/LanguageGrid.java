package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Language;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

public class LanguageGrid extends DSGrid<Language> {

    private static final long serialVersionUID = -8818716671121858460L;

    public LanguageGrid() {
        super(Language.class);
        withProperties("name", "script", "playable");
        withColumnHeaders("Nom", "Alphabet", "Jouable");

        Grid.Column playable = getColumn("playable");
        playable.setRenderer(new HtmlRenderer(),
                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
    }

}
