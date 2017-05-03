package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Ability;

public class AbilityGrid extends DSGrid<Ability> {

    private static final long serialVersionUID = 8060713315641761422L;

    public AbilityGrid() {
        super();
        addColumn(Ability::getName).setCaption("Nom").setId("name");
        addColumn(Ability::getAbbreviation).setCaption("Abbréviation").setId("abbreviation");
    }

}
