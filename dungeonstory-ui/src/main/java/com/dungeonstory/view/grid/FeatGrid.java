package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Feat;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.HtmlRenderer;

public class FeatGrid extends DSGrid<Feat> {

    private static final long serialVersionUID = -6577254670865533975L;

    public FeatGrid() {
        super(Feat.class);
        withProperties("name", "isClassFeature", "usage");
        withColumnHeaders("Nom", "Don de classe", "Usage");
        
        Grid.Column reach = getColumn("isClassFeature");
        reach.setRenderer(new HtmlRenderer(),
                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
    }

}
