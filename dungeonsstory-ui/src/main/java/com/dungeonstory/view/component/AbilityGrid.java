package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Ability;

public class AbilityGrid extends BeanGrid<Ability> {

    private static final long serialVersionUID = 8060713315641761422L;

    public AbilityGrid() {
        super(Ability.class);
        withColumns("name", "abbreviation", "description");
        withHeaderCaption("Nom", "Abbréviation", "Description");
    }

}
