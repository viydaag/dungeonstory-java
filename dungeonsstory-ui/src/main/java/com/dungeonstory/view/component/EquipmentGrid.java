package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Equipment;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.renderers.HtmlRenderer;

public class EquipmentGrid extends BeanGrid<Equipment> {

    private static final long serialVersionUID = 7341795764195713231L;

    public EquipmentGrid() {
        super(Equipment.class);
        withColumns("name", "type", "description", "isPurchasable", "isSellable");
        withHeaderCaption("Nom", "Type", "Description", "Achetable", "Vendable");

        getColumn("isPurchasable").setRenderer(new HtmlRenderer(),
                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));

        getColumn("isSellable").setRenderer(new HtmlRenderer(),
                new StringToBooleanConverter(FontAwesome.CHECK_CIRCLE_O.getHtml(), FontAwesome.CIRCLE_O.getHtml()));
    }

}
