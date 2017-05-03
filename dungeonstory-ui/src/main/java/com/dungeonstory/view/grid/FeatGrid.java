package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Feat;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.renderers.HtmlRenderer;

public class FeatGrid extends DSGrid<Feat> {

    private static final long serialVersionUID = -6577254670865533975L;

    public FeatGrid() {
        super();
        //        withProperties("name", "isClassFeature", "usage");
        //        withColumnHeaders("Nom", "Don de classe", "Usage");
        //        
        //        Grid.Column reach = getColumn("isClassFeature");
        //        reach.setRenderer(new HtmlRenderer(),
        //                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
        
        StringToBooleanConverter converter = new StringToBooleanConverter("", VaadinIcons.CHECK_CIRCLE_O.getHtml(),
                VaadinIcons.CIRCLE_THIN.getHtml());
        addColumn(Feat::getName).setCaption("Nom").setId("name");
        addColumn(feat -> converter.convertToPresentation(feat.getIsClassFeature(), new ValueContext()), new HtmlRenderer())
                .setCaption("Don de classe").setId("isClassFeature");
        addColumn(Feat::getUsage).setCaption("Usage").setId("usage");
    }

}
