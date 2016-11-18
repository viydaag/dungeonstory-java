package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Equipment;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.renderers.HtmlRenderer;

public class EquipmentGrid extends DSGrid<Equipment> {

    private static final long serialVersionUID = 7341795764195713231L;

    public EquipmentGrid() {
        super(Equipment.class);
        withProperties("name", "type", "isPurchasable", "isSellable");
        withColumnHeaders("Nom", "Type", "Achetable", "Vendable");

        getColumn("isPurchasable").setRenderer(new HtmlRenderer(),
                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));

        getColumn("isSellable").setRenderer(new HtmlRenderer(),
                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
    }

}
