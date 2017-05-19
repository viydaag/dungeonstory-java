package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Equipment;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToBooleanConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.renderers.HtmlRenderer;

public class EquipmentGrid extends DSGrid<Equipment> {

    private static final long serialVersionUID = 7341795764195713231L;

    public EquipmentGrid() {
        super();
        //        withProperties("name", "type", "isPurchasable", "isSellable");
        //        withColumnHeaders("Nom", "Type", "Achetable", "Vendable");
        //
        //        getColumn("isPurchasable").setRenderer(new HtmlRenderer(),
        //                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
        //
        //        getColumn("isSellable").setRenderer(new HtmlRenderer(),
        //                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));

        StringToBooleanConverter converter = new StringToBooleanConverter("", VaadinIcons.CHECK_CIRCLE_O.getHtml(),
                VaadinIcons.CIRCLE_THIN.getHtml());

        addColumn(Equipment::getName).setCaption("Nom").setId("name");
        addColumn(Equipment::getType).setCaption("Type").setId("type");
        addColumn(equipment -> converter.convertToPresentation(equipment.getIsPurchasable(), new ValueContext()), new HtmlRenderer())
                .setCaption("Achetable");
        addColumn(equipment -> converter.convertToPresentation(equipment.getIsSellable(), new ValueContext()), new HtmlRenderer())
                .setCaption("Vendable");
    }

}
