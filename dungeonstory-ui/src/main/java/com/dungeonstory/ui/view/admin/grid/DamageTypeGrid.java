package com.dungeonstory.ui.view.admin.grid;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.DamageType;
import com.vaadin.data.provider.ListDataProvider;

public class DamageTypeGrid extends ReadOnlyGrid<DamageType> {

    private static final long serialVersionUID = 1703049496748455895L;

    public DamageTypeGrid() {
        super();
        addColumn(DamageType::getName).setCaption("Nom").setId("name");
        addColumn(DamageType::getDescription).setCaption("Description").setId("description");
        setDataProvider(new ListDataProvider<>(EnumSet.allOf(DamageType.class)));
    }

}
