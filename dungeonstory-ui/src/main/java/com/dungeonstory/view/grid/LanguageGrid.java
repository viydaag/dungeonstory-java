package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Language;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.renderers.HtmlRenderer;

public class LanguageGrid extends DSGrid<Language> {

    private static final long serialVersionUID = -8818716671121858460L;

    public LanguageGrid() {
        super();
        StringToBooleanConverter converter = new StringToBooleanConverter("", VaadinIcons.CHECK_CIRCLE_O.getHtml(),
                VaadinIcons.CIRCLE_THIN.getHtml());
        addColumn(Language::getName).setCaption("Nom").setId("name");
        addColumn(Language::getScript).setCaption("Alphabet").setId("script");
        addColumn(language -> converter.convertToPresentation(language.getPlayable(), new ValueContext()), new HtmlRenderer()).setCaption("Jouable");
    }

}
