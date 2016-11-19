package com.dungeonstory.view.grid;

import com.dungeonstory.backend.data.Spell;

public class SpellGrid extends DSGrid<Spell> {

    private static final long serialVersionUID = 1178337698356790032L;

    public SpellGrid() {
        super(Spell.class);
        withProperties("level", "name", "school");
        withColumnHeaders("Niveau", "Nom", "École de magie");
    }

}
