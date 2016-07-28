package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.samples.crud.BeanGrid;

public class AbilityGrid extends BeanGrid<Ability> {

    private static final long serialVersionUID = 8060713315641761422L;

    public AbilityGrid() {
        super(Ability.class);
        withColumns("name", "abbreviation");
        withHeaderCaption("Nom", "Abbréviation");
    }

}
