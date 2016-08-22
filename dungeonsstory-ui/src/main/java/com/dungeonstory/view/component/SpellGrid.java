package com.dungeonstory.view.component;

import com.dungeonstory.backend.data.Spell;

public class SpellGrid extends BeanGrid<Spell> {

    private static final long serialVersionUID = 1178337698356790032L;

    public SpellGrid() {
        super(Spell.class);
        withColumns("level", "name", "school");
        withHeaderCaption("Niveau", "Nom", "École de magie");
    }

}
