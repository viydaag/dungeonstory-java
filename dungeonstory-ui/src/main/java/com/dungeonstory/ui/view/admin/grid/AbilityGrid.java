package com.dungeonstory.ui.view.admin.grid;

import java.util.EnumSet;

import com.dungeonstory.backend.data.enums.Ability;
import com.vaadin.data.provider.ListDataProvider;

public class AbilityGrid extends DSGrid<Ability> {

    private static final long serialVersionUID = 8060713315641761422L;

    public AbilityGrid() {
        super();
        addColumn(Ability::getName).setCaption("Nom").setId("name");
        addColumn(Ability::getAbbreviation).setCaption("Abbréviation").setId("abbreviation");
        
        setDataProvider(new ListDataProvider<>(EnumSet.allOf(Ability.class)));
    }

}
